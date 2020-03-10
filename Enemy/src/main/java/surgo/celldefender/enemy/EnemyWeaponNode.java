/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.enemy;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Enemy;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INode.class)
public class EnemyWeaponNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Enemy.class);
        super.registerComponent(Weapon.class);
        return this;
    }
}
