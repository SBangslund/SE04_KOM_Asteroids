/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.player;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.GameKeys;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class PlayerMovementSystem implements INodeSystem {
    
    @Override
    public void process(GameData gamedata, World world) {
        for (PlayerMovementNode node : world.getNodes(PlayerMovementNode.class)) {
            Velocity velocity = node.getComponent(Velocity.class);
            Rotation rotation = node.getComponent(Rotation.class);
            Moving moving = node.getComponent(Moving.class);
                        
            if(gamedata.getKeys().isDown(GameKeys.RIGHT)) {
                rotation.setRadians(rotation.getRadians() - moving.getRotationSpeed() * gamedata.getDelta());
            }
            
            if(gamedata.getKeys().isDown(GameKeys.LEFT)) {
                rotation.setRadians(rotation.getRadians() + moving.getRotationSpeed() * gamedata.getDelta());
            }
            
            if(gamedata.getKeys().isDown(GameKeys.UP)) {
                velocity.setDx(velocity.getDx() + (float)Math.cos(rotation.getRadians()) * moving.getAcceleration() * gamedata.getDelta());
                velocity.setDy(velocity.getDy() + (float)Math.sin(rotation.getRadians()) * moving.getAcceleration() * gamedata.getDelta());
            }
        }
    }
}
