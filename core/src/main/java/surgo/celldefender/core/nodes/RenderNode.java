/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.core.nodes;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Render;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class RenderNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Render.class);
        super.registerComponent(Position.class);
        return this;
    }
}
