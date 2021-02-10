package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.shape.Shape;

import static java.lang.Math.sqrt;


// parent for all projectiles
public abstract class Projectile extends Entity implements Collidable {

    int power = 0; // layers left that it can pop
    boolean canPopLeads = false; // probably make more of these
//    private int lives=1;

    private Balloon target;


//    public void handleCollision(Balloon b) {
////
////    } // special effects like burn freeze?


    @Override
    public void handleCollision() {
//        lives--;

    }

    Projectile(double x, double y, String textureName, Balloon target) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
        this.target = target;

    }

    @Override
    public void update(float delta) {

        double dx = target.getX() - this.x;
        double dy = target.getY()-this.y;
        double scaleFactor = sqrt(dx*dx + dy*dy);
        dx /= scaleFactor;
        dy /= scaleFactor;

        this.x += 20*dx;
        this.y +=20*dy;

    }

    public void fire(){
//        double dx = this.x - target.getX();
//        double dy = this.y - target.getY();
//        double mag = sqrt(dx*dx + dy*dy);
//        dx /= mag;
//        dy /= mag;
//
//        this.x += dx;
//        this.y +=dy;


    }

    public int getPower() {
        return power;
    }
}
