package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class MagicBall extends Projectile {

//    public MagicBall(double x, double y, double angle, String textureName, Balloon target) {
//        super(x, y, angle, textureName, target);
//        this.power = 5;
//
//    }

    public MagicBall(double x, double y, double angle,int power, String textureName, Balloon target) {
        super(x, y, angle, power, textureName, target);

    }

    @Override
    public Shape getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        return new Circle(x / 2, y / 2, radius / 2);
    }



    @Override
    public void handleCollision(Balloon b) {
        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (balloon.getX()> b.getX() -150 && balloon.getX()< b.getX() +150) {
                if (balloon.getY() > b.getY() - 150 && balloon.getY() < b.getY() +150) {
                    splashedBalloons.add(balloon);

                }
            }

        }

    }
}
