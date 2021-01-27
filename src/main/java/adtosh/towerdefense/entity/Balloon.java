package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;

public class Balloon extends Entity {

    public static String balloonFilePrefix = "balloon-";
//    double width;
//    double height;

    private int layers;
    private int currentPathPoint = 1;

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

    public Balloon(int layers, String texture) {
        super(texture);
//        App.currentGame.getLevel().addToBalloons(this);
        this.x = App.currentGame.getLevel().getPathPoint(0)[0];
        this.y = App.currentGame.getLevel().getPathPoint(0)[1];
        this.layers = layers;
        textureName = balloonFilePrefix + layers;
        //WIDTH AND HEIGHT IS IN ENTITY
//        width = TextureManager.getTexture(balloonFilePrefix + type).getWidth();
//        height = TextureManager.getTexture(balloonFilePrefix + type).getHeight();

    }

    public void handleCollision(Projectile p) { // todo


    }

    private void handleSpike() {

    }

    public void handleDefenseCollision(Iterator<Balloon> iterator) {

        this.layers--;

        if (layers <= 0) {
            this.remove(iterator);
        }else {
            this.textureName = balloonFilePrefix + layers;

        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x / 2, y / 2, width / 2, height / 2);

    }



    @Override
    public void update(float delta) {


        int[] pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
        double px = pointCoords[0] - x;
        double py = pointCoords[1] - y;
        double dX = 0;
        double dY = 0;


         double speed = SPEEDS[layers];
         //todo after spamming spikes array index out of bounds exception -1

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
            //This gets called somehow when the ballons gets destroyed and add to current paths points

//        if ((dX > px && px > 0) || (dY > py && py > 0) || (dY < py && py < 0) || (dX < px && px < 0)) {
            x = pointCoords[0];
            y = pointCoords[1];

            currentPathPoint++;

            if (currentPathPoint >= App.currentGame.getLevel().pathLength()) {
                App.currentGame.takeLives(layers);
                //todo sometimes doesnt get removed properly because array out of bound -1
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
