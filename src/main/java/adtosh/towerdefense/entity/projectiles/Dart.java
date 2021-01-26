package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public class Dart extends Projectile{


    public Dart(double x, double y, String texture) {
        super(x, y, texture);
    }


    @Override
    public Shape getBounds() {
        return null;
    }

    @Override
    public void update(float delta) {

    }
}
