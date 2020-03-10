/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgo.celldefender.common.components;

/**
 *
 * @author sbang
 */
public class Projectile extends GameComponent {
    private float damage;
    
    public Projectile(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }
}
