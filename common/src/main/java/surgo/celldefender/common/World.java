package surgo.celldefender.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import surgo.celldefender.common.components.GameComponent;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;
import surgo.celldefender.common.nodes.NodeList;

/**
 *
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private final NodeList<GameNode> nodeTypes = new NodeList();
    private final Map<Class, List<GameNode>> allNodes = new HashMap<>();
    
    public World() {
        for (INode node : Lookup.getDefault().lookupAll(INode.class)) {
            nodeTypes.add(node.registerNode());
        }
    }
    
    private List<GameNode> extractNodes(Map<Class, GameComponent> components) {
        List<GameNode> extractedNodes = new ArrayList<>();
        for (GameNode nodeType : nodeTypes.getNodes().values()) {
            if(components.keySet().containsAll(nodeType.getComponents().keySet())) {
                try {
                    GameNode node = nodeType.clone();
                    for(GameComponent component : components.values()) {
                        if(nodeType.getComponents().containsKey(component.getClass())) {
                            node.addComponent(component.getClass(), component);
                        }
                    }
                    extractedNodes.add(node);
                } catch (CloneNotSupportedException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }        
        return extractedNodes;
    }
    
    private void distributeNodes(List<GameNode> nodeList) {
        for (GameNode node : nodeList) {
            List<GameNode> gameNodes = allNodes.get(node.getClass());
            if(gameNodes != null) {
                gameNodes.add(node);
            } else {
                List<GameNode> newGameNodes = new ArrayList<>();
                newGameNodes.add(node);
                allNodes.put(node.getClass(), newGameNodes);
            }
        }
    }
    
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        distributeNodes(extractNodes(entity.getComponents()));
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Map<Class, List<GameNode>> getNodes() {
        return allNodes;
    }
    
    public <E extends GameNode> List<E> getNodes(Class<E> c) {
        List<E> result = (List<E>) allNodes.get(c);
        return result != null ? result : new ArrayList<>();
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
