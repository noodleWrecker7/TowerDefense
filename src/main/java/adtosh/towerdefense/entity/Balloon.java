package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;

public class Balloon extends Entity {

    static String balloonFilePrefix = "balloon-";

    int balloonType;
    int currentPathPoint = 1;
    double speed = 200; // pixels per second

    public Balloon(double x, double y, int type) {
        super(x, y);
        balloonType = type;

    }

    public void handleCollision(Projectile p) { // todo

    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(TextureManager.getTexture(balloonFilePrefix + balloonType), x, y);

        // just for testing
//         g.drawImage(TextureManager.getTexture("balloon"), x, y);
    }

    @Override
    public void update(float delta) {
        int[] pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
        double px = pointCoords[0] - x;
        double py = pointCoords[1] - y;

        double tanRes = Math.atan(py / px);
        if(px == 0) {
            tanRes = 90;
        }

        if (Double.isNaN(tanRes)) {
            tanRes = 90;
            System.out.println("nan");
        }
        double dX = Math.cos(tanRes) * speed * delta;
        double dY = Math.sin(tanRes) *speed * delta;

        if (dX > px || py > dY) {
            x = pointCoords[0];
            y = pointCoords[1];
            currentPathPoint++;
            return;
        }

        x += dX;
        y += dY;


    }
}
