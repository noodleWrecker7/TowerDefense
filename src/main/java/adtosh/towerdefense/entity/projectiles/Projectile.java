package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static java.lang.Math.decrementExact;
import static java.lang.Math.sqrt;


// parent for all projectiles
public abstract class Projectile extends Entity implements Collidable {

    protected int power; // layers left that it can pop
    boolean canPopLeads = false; // probably make more of these
//    private int lives=1;

    protected Balloon target;
    protected ArrayList<Balloon> splashedBalloons = new ArrayList<>();

    private double aimX;
    private double aimY;
    private double angle;

    private double dx;
    private double dy;



//    public void handleCollision(Balloon b) {
////
////    } // special effects like burn freeze?


//    @Override
//    public void handleCollision() {
//        splashedBalloons.add(target);
//
//    }

    public void handleCollision(Balloon balloon){
        splashedBalloons.add( balloon);

    }

    Projectile(double x, double y, double angle, String textureName, Balloon target) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
//        this.aimX = target.getX();
//        this.aimY = target.getY();
        this.target = target;
        findAim();
        this.dx = target.getX()- this.x;
        this.dy = target.getY()-this.y;
//        this.angle = angle;

        //todo THIS IS DODGY either stick to the aimbot or fix
//        findTarget();

    }

    private void findAim(){
        //find how much further infront to shoot distance wise(not displacment)
        //find where that point would be on the course

        //where will the ballon be in the time it take for projectile to reach the baloon
        //

    }



    @Override
    public void update(float delta) {

//        double dx = aimX - this.x;
//        double dy = aimY-this.y;
        double scaleFactor = sqrt(dx*dx + dy*dy);
        dx /= scaleFactor;
        dy /= scaleFactor;

        this.x += 25*dx;
        this.y +=25*dy;
        //temp low values

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

    public ArrayList<Balloon> getSplashedBalloons() {
        return splashedBalloons;
    }
}
