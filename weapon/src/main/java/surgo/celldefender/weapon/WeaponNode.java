/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.weapon;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Rotation;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class WeaponNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Weapon.class);
        super.registerComponent(Position.class);
        super.registerComponent(Rotation.class);
        return this;
    }
}
