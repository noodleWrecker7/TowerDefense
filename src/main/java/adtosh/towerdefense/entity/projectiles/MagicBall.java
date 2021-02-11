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

    public MagicBall(double x, double y, String textureName, Balloon target) {
        super(x, y, textureName, target);
        this.power = 5;
    }


    @Override
    public Shape getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        return new Circle(x / 2, y / 2, radius / 2);
    }

    @Override
    public void handleCollision() {

        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (balloon.getDistanceTravelled() < target.getDistanceTravelled() + 5 || balloon.getDistanceTravelled() > target.getDistanceTravelled() - 5) {
                splashedBalloons.add(balloon);
                //this adds all ballons in a radius of the target balloon to the balloon list that take damage

            }
        }


    }
}
