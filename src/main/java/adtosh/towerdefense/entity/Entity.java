package adtosh.towerdefense.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double x;
    protected double y;



//    public Entity(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
    public Entity(){

    }

    public abstract void render(GraphicsContext g);

    public abstract void update(float delta);


}
