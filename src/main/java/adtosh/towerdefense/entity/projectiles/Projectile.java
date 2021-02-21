package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
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

    private double dx;
    private double dy;




    @Override
    public void handleCollision(Balloon balloon){

        splashedBalloons.add( balloon);

    }


    Projectile(double x, double y, double angle, int power, String textureName, Balloon target) {
        super(x, y, textureName);
        App.currentGame.getLevel().addToProjectiles(this);
        this.target = target;
        this.angle = angle;
        this.power = power;
//        findAim();
        this.dx = target.getX()- this.x;
        this.dy = target.getY()-this.y;


    }

    public Projectile(double x, double y, double angle, int power, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToProjectiles(this);
        this.angle = angle;
        this.power = power;
        findAim();

    }

    private void findAim(){
        double gradient = Math.atan(angle);
        dy = cos(angle) * gradient;
        dx = sin(angle) * gradient;

    }



    @Override
    public void render(GraphicsContext g) {
        g.save();
        rotate(g, angle+ 257, x, y);
        super.render(g);
        g.restore();
    }

    @Override
    public void update(float delta) {

//        double dx = aimX - this.x;
//        double dy = aimY-this.y;
        double scaleFactor = sqrt(dx*dx + dy*dy);
        dx /= scaleFactor;
        dy /= scaleFactor;

//        this.x += 50*dx;
//        this.y +=50*dy;
        this.x += dx/10;
        this.y+=dy/10;
        //temp low values

    }


    public int getPower() {
        return power;
    }

    public ArrayList<Balloon> getSplashedBalloons() {
        return splashedBalloons;
    }

    public void clearSplashedBalloons(){
        splashedBalloons.clear();
    }
}
