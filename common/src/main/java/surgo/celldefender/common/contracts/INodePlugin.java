/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */
package surgo.celldefender.common.contracts;

import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;

/**
 *
 * @author sbang
 */
public interface INodePlugin {
    void start(GameData gamedata, World world);
    void stop(GameData gamedata, World world);
}
