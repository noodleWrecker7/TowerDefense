package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Dart extends Projectile{


//    public Dart(double x, double y, double angle, String texture, Balloon target) {
//        super(x, y, angle, texture, target);
//        this.power = 2;
//    }

    public Dart(double x, double y, double angle, int power, String textureName, Balloon target) {
        super(x, y, angle, power, textureName, target);
    }

    @Override
    public Shape getBounds() {
        return new Rectangle(this.x/2, this.y/2, TextureManager.getTexture(textureName).getWidth()/2, TextureManager.getTexture(textureName).getHeight()/2);
    }


}
