package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.turrets.SplashDamage;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Missile extends Projectile implements SplashDamage {

    private int splashDamageRange;


    public Missile(double x, double y, double angle, int power, int lives, boolean canPopLead, int splashDamageRange, String textureName, Balloon target) {
        super(x, y, angle, power, lives, canPopLead, textureName, target);
        this.correctiveAngle = 110;
        this.splashDamageRange = splashDamageRange;
    }

    @Override
    public Shape getBounds() {
        Image missile = TextureManager.getTexture(textureName);
        Rectangle rectangle =new Rectangle(this.x/2  - missile.getWidth()/4, this.y/2- missile.getHeight()/4, TextureManager.getTexture(textureName).getWidth()/2, TextureManager.getTexture(textureName).getHeight()/2);
//        ScreenManager.addRoot("game.fxml", rectangle);
        return rectangle;
    }

    @Override
    public void handleCollision(Balloon balloon) {
        dealSplashDamage(balloon, splashedBalloons, splashDamageRange);
        textureName = "explosion";
        this.lives--;

    }
}
