/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */

package surgo.celldefender.common.components;

/**
 *
 * @author Samuel Bangslund
 */
public class CollisionBox extends GameComponent {
    private float radius;
    
    public CollisionBox(float radius) {
        this.radius = radius;
    }
    
    public float getRadius() {
        return this.radius;
    }
}
