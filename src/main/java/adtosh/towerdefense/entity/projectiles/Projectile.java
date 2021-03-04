package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import static java.lang.Math.*;


// parent for all projectiles
public abstract class Projectile extends Entity implements Collidable, Rotatable {

    protected int power; // layers left that it can pop
    boolean canPopLeads = false; // probably make more of these
//    private int lives=1;

    protected Balloon target;
    protected ArrayList<Balloon> splashedBalloons = new ArrayList<>();

    private double aimX;
    private double aimY;
    private double angle;

    protected double correctiveAngle = 0;

    private double dx;
    private double dy;

    private boolean hit = false;

    protected int lives;

      //penetration




    @Override
    public void handleCollision(Balloon balloon) {

        splashedBalloons.add(balloon);
        this.lives--;

    }


    Projectile(double x, double y, double angle, int power, int lives, String textureName, Balloon target) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
        this.target = target;
        this.angle = angle;
        this.power = power;
        this.lives = lives;
//        findAim();
        this.dx = target.getX() - this.x;
        this.dy = target.getY() - this.y;


    }

    private BaseTurret source;

    public Projectile(double x, double y, double angle, int power, int lives, BaseTurret source, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToProjectiles(this);
        this.angle = angle;
        this.power = power;
        this.source = source;
        this.lives = lives;


        findAim();

    }



    private void findAim() {

        //this wont work because it should be when we do sin and cos the angle should be different (mostly 45)
//        double gradient = (tan(toRadians(angle)));
//        dy = cos(angle) * gradient;
//        dx = sin(angle) * gradient;





//        if (angle==90){
//            dy = 0;
//            dx = 1;
//        }
//        else if(angle == 270){
//            dy = 0;
//            dx=-1;
//        }else {
//
//            double gradient = (tan(toRadians(angle)));
//            dy = cos(angle) * gradient;
//            dx = sin(angle) * gradient;
//
//            if(gradient ==0) dy=1;
//        }
        //todo find a maths way to do this
        dy = 0;
        dx = 0;

        switch ((int) angle) {
            case 0:
                dy =-1;
                break;
            case 45:
                dy = -1;
                dx = 1;
                break;
            case 90:
                dx = 1;
                break;
            case 135:
                dx = 1;
                dy = 1;
                break;
            case 180:
                dy =1;
                break;
            case 225:
                dx = -1;
                dy = 1;
                break;
            case 270:
                dx = -1;
                break;
            case 315:
                dx = -1;
                dy = -1;
                break;
            default:
                System.out.println("BREAK");
                break;


        }

    }


    @Override
    public void render(GraphicsContext g) {
        g.save();
        rotate(g, angle + correctiveAngle, x, y);
        super.render(g);
        g.restore();
    }

    @Override
    public void update(float delta) {

//        double dx = aimX - this.x;
//        double dy = aimY-this.y;
        double scaleFactor = sqrt(dx * dx + dy * dy);
        dx /= scaleFactor;
        dy /= scaleFactor;

        this.x += 45*dx;
        this.y +=45*dy;
//        this.x += dx ;
//        this.y += dy ;
        //temp low values



    }




    public int getPower() {
        return power;
    }

    public ArrayList<Balloon> getSplashedBalloons() {
        return splashedBalloons;
    }

    public void clearSplashedBalloons() {
        splashedBalloons.clear();
    }

    public BaseTurret getSource() {
        return source;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getLives() {
        return lives;
    }
}
