/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.core.systems;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.openide.util.lookup.ServiceProvider;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.components.Position;
import surgo.celldefender.common.components.Render;
import surgo.celldefender.core.interfaces.IRenderNodeSystem;
import surgo.celldefender.core.nodes.RenderNode;

/**
 *
 * @author Samuel Bangslund
 */
@ServiceProvider(service = IRenderNodeSystem.class)
public class RenderSystem implements IRenderNodeSystem {
    
    @Override
    public void process(GameData gamedata, World world, ShapeRenderer sr) {
        for (RenderNode node : world.getNodes(RenderNode.class)) {
            Position position = node.getComponent(Position.class);
            Render render = node.getComponent(Render.class);
            
            sr.setColor(1, 1, 1, 1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            
            render.getDrawable().draw(render.getShapeX(), render.getShapeY(), render.getRotation());
            float[] shapex = render.getShapeX();
            float[] shapey = render.getShapeY();
            float x = position.getX();
            float y = position.getY();

            for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
                sr.line(x + shapex[i], y + shapey[i], x + shapex[j], y + shapey[j]);
            }
            
            sr.end();
        }
    }
}
