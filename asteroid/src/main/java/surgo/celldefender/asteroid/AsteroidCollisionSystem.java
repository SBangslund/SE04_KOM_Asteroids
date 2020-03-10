/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgo.celldefender.asteroid;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Asteroid;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Render;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author sbang
 */
@ServiceProvider(service = INodeSystem.class)
public class AsteroidCollisionSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        List<AsteroidCollisionNode> nodes = world.getNodes(AsteroidCollisionNode.class);
        List<Entity> markedForRemoval = new ArrayList<>();
        List<Entity> markedForAdding = new ArrayList<>();
        for (AsteroidCollisionNode asteroid : nodes) {            
            Position position = asteroid.getComponent(Position.class);
            Velocity velocity = asteroid.getComponent(Velocity.class);
            CollisionBox collision = asteroid.getComponent(CollisionBox.class);
            
            if(collision.isHit()) {
                markedForRemoval.add(asteroid.getParentEntity());
                if(collision.getRadius() >= 4) {
                    markedForAdding.add(createAsteroid(gamedata, collision.getRadius(), position, velocity));
                    markedForAdding.add(createAsteroid(gamedata, collision.getRadius(), position, velocity));
                }
            }
        }
        markedForRemoval.forEach(e -> world.removeEntity(e));
        markedForAdding.forEach(e -> world.addEntity(e));
    }

    private Entity createAsteroid(GameData gamedata, float radius, Position position, Velocity velocity) {
        Entity newAsteroid = new Entity();
        newAsteroid.add(new Asteroid());
        newAsteroid.add(new Position(position.getX(), position.getY()));
        newAsteroid.add(new Velocity(velocity.getDx() * (float) Math.random() * 4, velocity.getDy() * (float) Math.random() * 4));
        newAsteroid.add(new Moving(0f, 0f, 200, 10f));
        Rotation rotation = new Rotation((float) (Math.PI * 2 * Math.random()));
        newAsteroid.add(rotation);
        CollisionBox collision = new CollisionBox(radius - radius / 3, 0, 50);
        newAsteroid.add(collision);
        newAsteroid.add(new Render(new float[10], new float[10], rotation, (float[] shapex, float[] shapey, Rotation rot) -> {
            for (int j = 0; j < 10; j++) {
                shapex[j] = (float) Math.cos(2 * Math.PI * j / 10 + rot.getRadians()) * collision.getRadius();
                shapey[j] = (float) Math.sin(2 * Math.PI * j / 10 + rot.getRadians()) * collision.getRadius();
            }
        }));
        
        return newAsteroid;
    }
}
