package adtosh.towerdefense.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;


    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(GraphicsContext g);

    public abstract void update(float delta);


}
