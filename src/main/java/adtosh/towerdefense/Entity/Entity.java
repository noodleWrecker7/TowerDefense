package adtosh.towerdefense.Entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public abstract void render(GraphicsContext g);
    public abstract void update();

}
