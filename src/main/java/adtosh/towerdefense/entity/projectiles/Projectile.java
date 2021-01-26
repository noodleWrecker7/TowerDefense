package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;


// parent for all projectiles
public abstract class Projectile extends Entity {

    int power = 0; // layers left that it can pop
    float x = 0, y = 0;
    boolean canPopLeads = false; // probably make more of these


    public void handleCollision(Balloon b) {

    } // special effects like burn freeze?

    Projectile(double x, double y, String textureName) {
        super(x, y, textureName);
    }
}
