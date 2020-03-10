/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.player;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.GameKeys;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Weapon;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = INodeSystem.class)
public class PlayerWeaponSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        List<PlayerWeaponNode> nodes = world.getNodes(PlayerWeaponNode.class);
        for (PlayerWeaponNode node : nodes) {
            Weapon weapon = node.getComponent(Weapon.class);
            if(gamedata.getKeys().isDown(GameKeys.SPACE)) {
                weapon.setFiring(true);
            }
        }
    }
}
