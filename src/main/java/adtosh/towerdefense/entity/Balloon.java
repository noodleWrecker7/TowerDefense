package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;

public class Balloon extends Entity {

    static String balloonFilePrefix = "balloon-";
    double width;
    double height;

    int layers;
    int currentPathPoint = 1;

    final static double[] SPEEDS = { // pixels per seconds
            75, 100, 125, 125, 200, 250, 700
    };

//    public Balloon(double x, double y, int type) {
////        super(x, y);
////        layers = type;
////        width = TextureManager.getTexture(balloonFilePrefix + type).getWidth();
////        height = TextureManager.getTexture(balloonFilePrefix + type).getHeight();
////
////    }
    public Balloon(int type){
        super();
        this.x = App.currentGame.getLevel().getPathPoint(0)[0];
        this.y = App.currentGame.getLevel().getPathPoint(0)[1];


        this.layers = type;
        width = TextureManager.getTexture(balloonFilePrefix + type).getWidth();
        height = TextureManager.getTexture(balloonFilePrefix + type).getHeight();

    }

    public void handleCollision(Projectile p) { // todo

    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(TextureManager.getTexture(balloonFilePrefix + layers), x - width / 2, y - height / 2);

    }

    @Override
    public void update(float delta) {
        int[] pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
        double px = pointCoords[0] - x;
        double py = pointCoords[1] - y;
        double dX = 0;
        double dY = 0;

        double speed = SPEEDS[layers];

        if (py == 0) {
            if (px > 0) {
                dX = speed * delta;
            } else {
                dX = -speed * delta;
            }

        } else if (px == 0) {
            if (py > 0) {
                dY = speed * delta;
            } else {
                dY = -speed * delta;
            }

        } else {
            System.out.println("trig");

            double tanRes = Math.atan(py / px);
            if (px == 0) {
                tanRes = 90;
            }

            if (Double.isNaN(tanRes)) {
                tanRes = 90;
                System.out.println("nan");
            }
            dX = Math.cos(tanRes) * speed * delta;
            dY = Math.sin(tanRes) * speed * delta;
        }

        if (Math.abs(dX) > Math.abs(px) || Math.abs(dY) > Math.abs(py)) {
//        if ((dX > px && px > 0) || (dY > py && py > 0) || (dY < py && py < 0) || (dX < px && px < 0)) {
            x = pointCoords[0];
            y = pointCoords[1];
            currentPathPoint++;
            if (currentPathPoint >= App.currentGame.getLevel().pathLength()) {
                App.currentGame.takeLives(layers);
                layers = -1;
            }
            return;
        }

        x += dX;
        y += dY;
    }

    public int getLayers() {
        return layers;
    }
}
