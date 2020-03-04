/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.player;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Player;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Render;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.contracts.INodePlugin;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodePlugin.class)
public class PlayerPlugin implements INodePlugin {

    @Override
    public void start(GameData gamedata, World world) {
        Entity player = new Entity();
        player.add(new Position(gamedata.getDisplayWidth() / 2, gamedata.getDisplayHeight() / 2));
        player.add(new Velocity(0f, 0f));
        player.add(new CollisionBox(10f));
        player.add(new Moving(50f, 150f, 200f, 6f));
        player.add(new Player());
        Rotation rotation = new Rotation((float) Math.PI / 2);
        player.add(rotation);
        player.add(new Render(new float[4], new float[4], rotation, (float[] shapex, float[] shapey, Rotation rot) -> {
            float radians = rot.getRadians();
                shapex[0] = (float) (Math.cos(radians) * 8);
                shapey[0] = (float) (Math.sin(radians) * 8);

                shapex[1] = (float) (Math.cos(radians - 4 * 3.1415f / 5) * 8);
                shapey[1] = (float) (Math.sin(radians - 4 * 3.1145f / 5) * 8);

                shapex[2] = (float) (Math.cos(radians + 3.1415f) * 5);
                shapey[2] = (float) (Math.sin(radians + 3.1415f) * 5);

                shapex[3] = (float) (Math.cos(radians + 4 * 3.1415f / 5) * 8);
                shapey[3] = (float) (Math.sin(radians + 4 * 3.1415f / 5) * 8);
        }));
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gamedata, World world) {
        
    }

}
