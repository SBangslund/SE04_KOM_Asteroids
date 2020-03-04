/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.common.nodes;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Moving;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Velocity;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class MovementNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Position.class);
        super.registerComponent(Velocity.class);
        super.registerComponent(Moving.class);
        return this;
    }
}
