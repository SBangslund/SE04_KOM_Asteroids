/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgo.celldefender.asteroid;

import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.components.Asteroid;
import surgo.celldefender.common.components.CollisionBox;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Velocity;
import surgo.celldefender.common.nodes.GameNode;
import surgo.celldefender.common.nodes.INode;

/**
 *
 * @author sbang
 */
@ServiceProvider(service = INode.class)
public class AsteroidCollisionNode extends GameNode {

    @Override
    public GameNode registerNode() {
        super.registerComponent(Asteroid.class);
        super.registerComponent(CollisionBox.class);
        super.registerComponent(Position.class);
        super.registerComponent(Velocity.class);
        return this;
    }
}
