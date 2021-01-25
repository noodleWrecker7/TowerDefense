package adtosh.towerdefense.entity;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.Spike;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
    protected double x;
    protected double y;

    protected double width;
    protected double height;


    protected Rectangle hitBox ;




    public Entity(){
        Level.entities.add(this);

    }

    public Entity(double x, double y, Image texture){
        this(texture);
        this.x = x;
        this.y = y;
//        this.width = texture.getWidth();
//        this.height = texture.getHeight();
//        hitBox= new Rectangle(x, y, width, height );
        //constructor chaining so that not every entity needs an x and y passed

    }

    public Entity(Image texture){
        this();
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        hitBox= new Rectangle(x, y, width/2, height/2 );

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
