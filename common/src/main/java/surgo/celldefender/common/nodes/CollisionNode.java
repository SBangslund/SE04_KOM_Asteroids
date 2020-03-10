/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.common.nodes;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Position;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class CollisionNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Position.class);
        super.registerComponent(CollisionBox.class);
        return this;
    }
}
