package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;


// parent for all projectiles
public abstract class Projectile extends Entity {

    int power = 0; // layers left that it can pop
    float x = 0, y = 0;
    boolean canPopLeads = false; // probably make more of these
    private BaseTurret source;
    private Balloon target;


    public void handleCollision(Balloon b) {

    } // special effects like burn freeze?

    Projectile(double x, double y, String textureName, BaseTurret source) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
        this.source = source;
        this.target = source.getTarget();

    }

    public void fire(){

        System.out.println("FIRE");

    }
}
