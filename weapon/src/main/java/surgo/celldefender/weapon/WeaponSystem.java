/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.weapon;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Projectile;
import surgo.celldefender.common.components.Render;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Timer;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.components.interfaces.Drawable;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class WeaponSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        List<WeaponNode> nodes = world.getNodes(WeaponNode.class);
        List<Entity> markedForAdding = new ArrayList<>();

        for (WeaponNode node : nodes) {
            Weapon weapon = node.getComponent(Weapon.class);
            Position position = node.getComponent(Position.class);
            Rotation rotation = node.getComponent(Rotation.class);
            if(weapon.isFiring()) {
                markedForAdding.add(createProjectile(rotation, position));
                weapon.setFiring(false);
            }
        }
        markedForAdding.forEach(e -> world.addEntity(e));
    }
    
    private Entity createProjectile(Rotation rotation, Position position) {
        Entity projectile = new Entity();
        projectile.add(new Position(position.getX(), position.getY()));
        Rotation newRot = new Rotation(rotation.getRadians());
        projectile.add(newRot);
        projectile.add(new Moving(2, 150, 300, 20));
        projectile.add(new Projectile(150));
        projectile.add(new CollisionBox(2, 0, 1000));
        projectile.add(new Timer(6000));
        projectile.add(new Velocity((float)Math.cos(rotation.getRadians()) * 300, (float) Math.sin(rotation.getRadians()) * 300));
        projectile.add(new Render(new float[2], new float[2], newRot, new Drawable() {
            @Override
            public void draw(float[] shapex, float[] shapey, Rotation rotation) {
                shapex[0] = (float) Math.cos(rotation.getRadians());
                shapey[0] = (float) Math.sin(rotation.getRadians());
                shapex[1] = (float) Math.cos(rotation.getRadians()) * 4;
                shapey[1] = (float) Math.sin(rotation.getRadians()) * 4;
            }
        }));
        return projectile;
    }
}
