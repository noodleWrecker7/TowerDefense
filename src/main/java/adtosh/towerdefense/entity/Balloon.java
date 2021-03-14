package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.Game;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.sqrt;

public class Balloon extends Entity {

    public static String balloonFilePrefix = "balloon-";
//    double width;
//    double height;

    private int layers;
    private final int originalLayer;
    //    private int currentPathPoint = 1;
    private int currentPathPoint = 0;

    private boolean leader = false;

    private double distanceTravelled = 0;
    private long startTime;
    private long elapsedTime;

    private String relationX = "equal";
    private String relationY = "equal";
    private int[] pointCoords = App.currentGame.getLevel().getPathPoint(0);

//    private double dX =0;
//    private double dY =0;

    private double px, py;


        final static double[] speeds = { // pixels per seconds
            75, 100, 125, 125, 200, 250, 500, 300


    };




    public Balloon(int layers, String texture) {
        super(texture);
        App.currentGame.getLevel().addToBalloons(this);
        this.x = App.currentGame.getLevel().getPathPoint(0)[0];
        this.y = App.currentGame.getLevel().getPathPoint(0)[1];
        this.layers = layers;
        this.originalLayer = layers;
        textureName = balloonFilePrefix + layers;
        this.startTime = System.nanoTime();



    }

    public void handleCollision(Projectile p) {
        int layerBefore = layers;
        this.layers -= p.getPower();
        int currentBalance = App.currentGame.getLevel().getMoney();

        if (layers<0){
            App.currentGame.getLevel().setMoney(currentBalance+ layerBefore+1);
            //layers are zero indexed so add 1
        }else {
            App.currentGame.getLevel().setMoney(currentBalance+ p.getPower());
        }

        this.textureName = balloonFilePrefix + layers;
    }





    public void handleSpikeCollision() {

        this.layers--;
        this.textureName = balloonFilePrefix + layers;
        App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney()+1);


    }

    @Override
    public Rectangle getBounds() {

        Image balloonImage = TextureManager.getTexture(textureName);
        Rectangle rectangle = new Rectangle(x / 2 - balloonImage.getWidth()/2, y / 2 - balloonImage.getHeight()/2, width / 2, height / 2);
//        ScreenManager.addRoot("game.fxml", rectangle);
        return  rectangle;


    }


    @Override
    public void update(double delta) {

//        if (pointCoords == null) {
//
//            currentPathPoint++;
//            if (currentPathPoint >= App.currentGame.getLevel().pathLength()) {
//                App.currentGame.takeLives(layers);
//                layers = -1;
//                return;
//
//            }
//            pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
//
//            if (x > pointCoords[0]) {
//                //going left
//                relationX = "greater";
//            } else if (x < pointCoords[0]) {
//                relationX = "smaller";
//            } else {
//                relationX = "equal";
//            }
//
//            if (y > pointCoords[1]) {
//                //going left
//                relationY = "greater";
//            } else if (y < pointCoords[1]) {
//                relationY = "smaller";
//            } else {
//                relationY = "equal";
//            }
//
//            px = (pointCoords[0] - x) * delta;
//            py = (pointCoords[1] - y) * delta;
//
//        }

        double speed = speeds[layers];
        boolean xReached = false, yReached = false;


        if (relationX.equals("greater") && x < pointCoords[0]) xReached = true;
        if (relationX.equals("smaller") && x > pointCoords[0]) xReached = true;
        if (relationX.equals("equal") && x == pointCoords[0]) xReached = true;

        if (relationY.equals("greater") && y < pointCoords[1]) yReached = true;
        if (relationY.equals("smaller") && y > pointCoords[1]) yReached = true;
        if (relationY.equals("equal") && y == pointCoords[1]) yReached = true;





        if (xReached && yReached) {
            this.x = pointCoords[0];
            this.y = pointCoords[1];
            increasePointCoords();
//            pointCoords = null;
        }

        distanceTravelled+= speed * delta;

        double scaleFactor = sqrt(px * px + py * py);
        px /= scaleFactor;
        py /= scaleFactor;
       

        this.x += px * speed * delta;
        this.y += py * speed * delta;


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

    private void increasePointCoords(){
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

        px = (pointCoords[0] - x);
        py = (pointCoords[1] - y);

    }




    public double getDistanceTravelled() {
        return distanceTravelled;
    }



    public int getLayers() {
        return layers;
    }

    public int getOriginalLayer() {
        return originalLayer;
    }
}
