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
public interface INodeSystem {
    void process(GameData gamedata, World world);
}
