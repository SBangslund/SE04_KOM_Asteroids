/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */
package surgo.celldefender.common.components.interfaces;

import surgo.celldefender.common.components.Rotation;

/**
 *
 * @author sbang
 */
public interface Drawable {
    void draw(float[] shapex, float[] shapey, Rotation rotation);
}
