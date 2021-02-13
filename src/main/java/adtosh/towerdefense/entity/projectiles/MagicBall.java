package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class MagicBall extends Projectile {
    private int lives;

    public MagicBall(double x, double y, double angle, String textureName, Balloon target) {
        super(x, y, angle, textureName, target);
        this.power = 5;
    }


    @Override
    public Shape getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        return new Circle(x / 2, y / 2, radius / 2);
    }

    @Override
    public void handleCollision() {
        //todo this method is unsuitable when the ballons cros over paths

        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (balloon.getX()> target.getX() -150 && balloon.getX()< target.getX() +150) {
                if (balloon.getY() > target.getY() - 150 && balloon.getY() < target.getY() +150) {
                    splashedBalloons.add(balloon);

                }
            }

        }


    }
}
