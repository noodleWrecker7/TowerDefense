package adtosh.towerdefense.entity;

import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.Spike;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double x;
    protected double y;




    public Entity(){
        Level.entities.add(this);
    }

    public Entity(double x, double y){
        this();
        this.x = x;
        this.y = y;
        //constructor chaining so that not every entity needs an x and y passed

    }

    public abstract void render(GraphicsContext g);

    public abstract void update(float delta);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
