/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.player;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Player;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class PlayerWeaponNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Player.class);
        super.registerComponent(Weapon.class);
        return this;
    }
}
