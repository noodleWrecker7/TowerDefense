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



//    public void handleCollision(Balloon b) {
////
////    } // special effects like burn freeze?


    @Override
    public void handleCollision() {
        splashedBalloons.add(target);

    }

    Projectile(double x, double y, double angle, String textureName, Balloon target) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
        this.aimX = target.getX();
        this.aimY = target.getY();
        this.target = target;
//        this.angle = angle;

        //todo THIS IS DODGY either stick to the aimbot or fix
//        findTarget();

    }

    private void findTarget(){
//        double deltaX = App.canvasWidth - this.x / target.getX();
//        double deltaY = App.canvasHeight- this.y/ target.getY();


        double dx = this.x - target.getX();

        double invertedY = 600 -y;
        double invertedYT = 600 - target.getY();
        double dy = invertedY - invertedYT;
        double gradient = (dy / dx);
        //x1 - x2 = m(y1-y2)


        double c = invertedY/ (gradient * x);
        System.out.println(gradient);

        //y = gradient x + c
        double yIntercept = c;
        double xIntercept = ( y -c)/gradient;


        //where it intersects x left border
//        double tempAimX = xIntercept;
//        double tempAimY = 0;

        aimX = xIntercept;
        aimY = 0;



        //where it intersects y

//        double tempAimX2 = 0;
//        double tempAimY2 = yIntercept;

//        double yDistance= tempAimY - this.y;
//        double xDistance = tempAimX -this.x;
//
//        double yDistance2= tempAimY2 - this.y;
//        double xDistance2 = tempAimX2 -this.x;
//
//        double distance1= sqrt(yDistance * yDistance + xDistance * xDistance);
//        double distance2= sqrt(xDistance2 * xDistance2 + yDistance2 * yDistance2);
//
//        if (distance1>= distance2 ){
//            this.aimX = tempAimX2;
//            this.aimY = tempAimY2;
//        }else {
//            this.aimX = tempAimX;
//            this.aimY = tempAimY;
//
//        }

        //dont forget to 2x




        //todo keep going until it would reach y axis, doesnt matter which axis we use


    }

    @Override
    public void update(float delta) {

        double dx = aimX - this.x;
        double dy = aimY-this.y;
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
