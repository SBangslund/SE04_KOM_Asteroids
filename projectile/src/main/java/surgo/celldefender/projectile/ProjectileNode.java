/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.projectile;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Projectile;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class ProjectileNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Projectile.class);
        super.registerComponent(Position.class);
        super.registerComponent(Velocity.class);
        super.registerComponent(CollisionBox.class);
        return this;
    }

}
