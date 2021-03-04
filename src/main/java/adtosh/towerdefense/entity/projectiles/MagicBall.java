package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.SplashDamage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MagicBall extends Projectile implements SplashDamage {

    private int spashDamageRange;



    public MagicBall(double x, double y, double angle,int power,int lives, int splashDamageRange, String textureName, Balloon target) {
        super(x, y, angle, power,lives, textureName, target);
        this.spashDamageRange = splashDamageRange;

    }

    @Override
    public Shape getBounds() {


        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        Circle circle =new Circle(x / 2 , y / 2 , radius / 2);
//        ScreenManager.addRoot("game.fxml", circle);
        return circle;

    }



    @Override
    public void handleCollision(Balloon b) {
        dealSplashDamage(b, splashedBalloons, spashDamageRange);
        this.lives--;
//        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
//            if (balloon.getX()> b.getX() -150 && balloon.getX()< b.getX() +150) {
//                if (balloon.getY() > b.getY() - 150 && balloon.getY() < b.getY() +150) {
//                    splashedBalloons.add(balloon);
//
//                }
//            }
//
//        }

    }
}
