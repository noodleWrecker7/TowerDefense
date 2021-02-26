package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;

public class Dart extends Projectile{



//    public Dart(double x, double y, double angle, String texture, Balloon target) {
//        super(x, y, angle, texture, target);
//        this.power = 2;
//    }

    public Dart(double x, double y, double angle, int power, int lives, String textureName, Balloon target) {
        super(x, y, angle, power, lives,textureName,  target);
    }

    public Dart(double x, double y, double angle, int power,int lives, BaseTurret source, String texture) {
        super(x, y,angle, power, lives, source,  texture);
    }

    @Override
    public Rectangle getBounds() {
        Image dart =TextureManager.getTexture(textureName);
        Rectangle rectangle =new Rectangle(this.x/2  - dart.getWidth()/4, this.y/2- dart.getHeight()/4, TextureManager.getTexture(textureName).getWidth()/2, TextureManager.getTexture(textureName).getHeight()/2);
//        ScreenManager.addRoot("game.fxml", rectangle);
        return rectangle;
    }


}
