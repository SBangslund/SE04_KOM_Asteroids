/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.enemy;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class EnemyWeaponSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        for (EnemyWeaponNode node : world.getNodes(EnemyWeaponNode.class)) {
            Weapon weapon = node.getComponent(Weapon.class);
            if(Math.random() < 0.1) {
                weapon.setFiring(true);
            }
        }
    }
}
