package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public class Dart extends Projectile{


    public Dart(double x, double y, double angle, String texture, Balloon target) {
        super(x, y, angle, texture, target);
    }


    @Override
    public Shape getBounds() {
        return null;
    }

    @Override
    public void update(float delta) {

    }
}
