package adtosh.towerdefense.entity;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Balloon extends Entity {

    static String balloonFilePrefix = "balloon-";

    int balloonType;

    public Balloon(double x, double y, int type) {
        super(x, y);
        balloonType = type;

    }

    public void handleCollision(Projectile p) { // todo

    }

    @Override
    public void render(GraphicsContext g) {
        // this is what it should be
         g.drawImage(TextureManager.getTexture(balloonFilePrefix+balloonType), x, y, width, height);

        // just for testing
//         g.drawImage(TextureManager.getTexture("balloon"), x, y);
    }

    @Override
    public void update(float delta) {


    }
}
