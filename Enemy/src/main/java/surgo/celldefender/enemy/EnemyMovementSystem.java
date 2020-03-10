/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.enemy;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Enemy;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class EnemyMovementSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        for (EnemyMovementNode node : world.getNodes(EnemyMovementNode.class)) {
            Velocity velocity = node.getComponent(Velocity.class);
            Rotation rotation = node.getComponent(Rotation.class);
            Moving moving = node.getComponent(Moving.class);
            Enemy enemy = node.getComponent(Enemy.class);
            
            double direction = Math.random();
            
            if(!enemy.isInCooldown()) {
                if(direction <= 0.5) {
                    rotation.setRadians(rotation.getRadians() - moving.getRotationSpeed() * gamedata.getDelta());
                }

                if(direction >= 0.5 && direction < 1) {
                    rotation.setRadians(rotation.getRadians() + moving.getRotationSpeed() * gamedata.getDelta());
                }

                if(direction >= 0.3 && direction <= 0.7) {
                    velocity.setDx(velocity.getDx() + (float)Math.cos(rotation.getRadians()) * moving.getAcceleration());
                    velocity.setDy(velocity.getDy() + (float)Math.sin(rotation.getRadians()) * moving.getAcceleration());
                }
                enemy.startCooldown();
            }
        }
    }
}
