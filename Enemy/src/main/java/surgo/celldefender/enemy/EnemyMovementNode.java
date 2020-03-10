/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.enemy;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Enemy;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class EnemyMovementNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Enemy.class);
        super.registerComponent(Position.class);
        super.registerComponent(Velocity.class);
        super.registerComponent(Rotation.class);
        super.registerComponent(Moving.class);
        return this;
    }
}
