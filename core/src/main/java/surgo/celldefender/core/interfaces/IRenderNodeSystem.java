/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */
package surgo.celldefender.core.interfaces;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;

/**
 *
 * @author sbang
 */
public interface IRenderNodeSystem {
    void process(GameData gameData, World world, ShapeRenderer sr);
}
