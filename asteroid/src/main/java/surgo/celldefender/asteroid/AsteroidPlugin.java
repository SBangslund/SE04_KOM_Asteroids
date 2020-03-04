/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.asteroid;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Asteroid;
import surgo.celldefender.common.components.Moving;
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
public class AsteroidPlugin implements INodePlugin {

    @Override
    public void start(GameData gamedata, World world) {
        Random random = new Random(1234135);
        for (int i = 0; i < 1000; i++) {
            Entity asteroid = new Entity();
            Asteroid ast = new Asteroid(5 * (float) Math.random() + 2);
            asteroid.add(ast);
            asteroid.add(new Position((float) Math.random() * gamedata.getDisplayWidth(), (float) Math.random() * gamedata.getDisplayHeight()));
            asteroid.add(new Velocity(random.nextBoolean() ? 1 * (float) Math.random() * 100: - 1 * (float) Math.random() * 100, random.nextBoolean() ? 1 * (float) Math.random() * 100: - 1 * (float) Math.random() * 100));
            asteroid.add(new Moving(0f, 0f, 200, 10f));
            Rotation rotation = new Rotation((float) (Math.PI * 2 * Math.random()));
            asteroid.add(rotation);
            asteroid.add(new Render(new float[10], new float[10], rotation, (float[] shapex, float[] shapey, Rotation rot) -> {
                for (int j = 0; j < 10; j++) {
                    shapex[j] = (float) Math.cos(2 * Math.PI * j / 10 + rot.getRadians()) * ast.getRadius();
                    shapey[j] = (float) Math.sin(2 * Math.PI * j / 10 + rot.getRadians()) * ast.getRadius();
                }
            }));
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gamedata, World world) {

    }

}
