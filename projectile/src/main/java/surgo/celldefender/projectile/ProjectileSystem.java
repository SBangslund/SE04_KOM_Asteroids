/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgo.celldefender.projectile;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.Entity;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.contracts.INodeSystem;

/**
 *
 * @author sbang
 */
@ServiceProvider(service = INodeSystem.class)
public class ProjectileSystem implements INodeSystem {

    @Override
    public void process(GameData gamedata, World world) {
        List<ProjectileNode> projectileNodes = world.getNodes(ProjectileNode.class);
        List<Entity> markedForRemoval = new ArrayList<>();
        for (ProjectileNode projectileNode : projectileNodes) {
            CollisionBox col = projectileNode.getComponent(CollisionBox.class);
            if(col.isHit()) {
                markedForRemoval.add(projectileNode.getParentEntity());
            }
        }
        markedForRemoval.forEach(e -> world.removeEntity(e));
    }
}
