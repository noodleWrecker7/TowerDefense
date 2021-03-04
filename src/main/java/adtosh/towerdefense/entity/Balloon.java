package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.sqrt;

public class Balloon extends Entity {

    public static String balloonFilePrefix = "balloon-";
//    double width;
//    double height;

    private int layers;
    //    private int currentPathPoint = 1;
    private int currentPathPoint = 0;

    private boolean leader = false;

    private double distanceTravelled = 0;
    private long startTime;
    private long elapsedTime;

    private String relationX;
    private String relationY;
    private int[] pointCoords;

//    private double dX =0;
//    private double dY =0;

    private double px, py;


//        final static double[] SPEEDS = { // pixels per seconds
//            7.5, 10.0, 12.5, 12.5, 20.0, 25.0, 70.0
//
//
//    };
    final static double[] SPEEDS = { // pixels per seconds
            0.75, 1.00, 1.25, 1.25, 2.00, 2.50, 7.0


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
        App.currentGame.getLevel().addToBalloons(this);
        this.x = App.currentGame.getLevel().getPathPoint(0)[0];
        this.y = App.currentGame.getLevel().getPathPoint(0)[1];
        this.layers = layers;
        textureName = balloonFilePrefix + layers;
        this.startTime = System.nanoTime();



    }

    public void handleCollision(Projectile p) {
        this.layers -= p.getPower();
        this.textureName = balloonFilePrefix + layers;
    }


//    public void handleCollision() {
//        this.layers--;
//        this.textureName = balloonFilePrefix + layers;
//
//    }


    public void handleSpikeCollision() {

        this.layers--;
        this.textureName = balloonFilePrefix + layers;


    }

    @Override
    public Rectangle getBounds() {

        Image balloonImage = TextureManager.getTexture(textureName);
        Rectangle rectangle = new Rectangle(x / 2 - balloonImage.getWidth()/2, y / 2 - balloonImage.getHeight()/2, width / 2, height / 2);
//        ScreenManager.addRoot("game.fxml", rectangle);
        return  rectangle;


    }


    @Override
    public void update(float delta) {

        if (pointCoords == null) {

            currentPathPoint++;
            if (currentPathPoint >= App.currentGame.getLevel().pathLength()) {
                App.currentGame.takeLives(layers);
                layers = -1;
                return;

            }
            pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);

            if (x > pointCoords[0]) {
                //going left
                relationX = "greater";
            } else if (x < pointCoords[0]) {
                relationX = "smaller";
            } else {
                relationX = "equal";
            }

            if (y > pointCoords[1]) {
                //going left
                relationY = "greater";
            } else if (y < pointCoords[1]) {
                relationY = "smaller";
            } else {
                relationY = "equal";
            }

            px = (pointCoords[0] - x) * delta;
            py = (pointCoords[1] - y) * delta;

        }

        double speed = SPEEDS[layers];

        double scaleFactor = sqrt(px * px + py * py);
        if (scaleFactor == 0) {
            System.out.println("HERE");

        }
        px /= scaleFactor;
        py /= scaleFactor;

        this.x += px * speed ;
        this.y += py * speed ;


        boolean xReached = false, yReached = false;

        if (relationX.equals("greater") && x < pointCoords[0]) xReached = true;
        if (relationX.equals("smaller") && x > pointCoords[0]) xReached = true;
        if (relationX.equals("equal") && x == pointCoords[0]) xReached = true;

        if (relationY.equals("greater") && y < pointCoords[1]) yReached = true;
        if (relationY.equals("smaller") && y > pointCoords[1]) yReached = true;
        if (relationY.equals("equal") && y == pointCoords[1]) yReached = true;





        if (xReached && yReached) {
            pointCoords = null;
        }

        distanceTravelled+= speed * delta;
       

        this.x += px;
        this.y += py;


//        if (py == 0) {
//            if (px > 0) {
//                dX = speed * delta;
//            } else {
//                dX = -speed * delta;
//            }
//
//        } else if (px == 0) {
//            if (py > 0) {
//                dY = speed * delta;
//            } else {
//                dY = -speed * delta;
//            }
//
//        } else {
//            System.out.println("trig");
//
//            double tanRes = Math.atan(py / px);
//            if (px == 0) {
//                tanRes = 90;
//            }
//
//            if (Double.isNaN(tanRes)) {
//                tanRes = 90;
//                System.out.println("nan");
//            }
//            dX = Math.cos(tanRes) * speed * delta;
//            dY = Math.sin(tanRes) * speed * delta;
//        }
//
//        if (Math.abs(dX) > Math.abs(px) || Math.abs(dY) > Math.abs(py)) {
//            //This gets called somehow when the ballons gets destroyed and add to current paths points
//
////        if ((dX > px && px > 0) || (dY > py && py > 0) || (dY < py && py < 0) || (dX < px && px < 0)) {
//            x = pointCoords[0];
//            y = pointCoords[1];
//
//            currentPathPoint++;
//
//            if (currentPathPoint >= App.currentGame.getLevel().pathLength()) {
//                App.currentGame.takeLives(layers);
//                //todo sometimes doesnt get removed properly because array out of bound -1
//                layers = -1;
//
//            }
//            return;
//        }
//
//        x += dX;
//        y += dY;

        //todo remember distance






    }



    public double getDistanceTravelled() {
        return distanceTravelled;
    }



    public int getLayers() {
        return layers;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }
}
