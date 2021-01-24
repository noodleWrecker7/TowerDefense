package adtosh.towerdefense.entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(GraphicsContext g);

    public abstract void update(float delta);

}
