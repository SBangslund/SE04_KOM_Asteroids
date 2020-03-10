/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.common.systems;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Timer;
import surgo.celldefender.common.contracts.INodeSystem;
import surgo.celldefender.common.nodes.TimerNode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class TimerSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        List<TimerNode> nodes = world.getNodes(TimerNode.class);
        List<Entity> markedForRemoval = new ArrayList<>();
        for (TimerNode node : nodes) {
            Timer timer = node.getComponent(Timer.class);
            if(timer.isExpired()) {
                markedForRemoval.add(node.getParentEntity());
            }
        }
        markedForRemoval.forEach(e -> world.removeEntity(e));
    }
}
