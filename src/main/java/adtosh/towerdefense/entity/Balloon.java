package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;

public class Balloon extends Entity {

    static String balloonFilePrefix = "balloon-";

    private int layers;
    private int currentPathPoint = 1;
    private double speed = 200; // pixels per second

    private int velX;
    private int velY;

    public Balloon(double x, double y, int type) {
        super(x, y);
        layers = type;

    }

    public void handleCollision(Projectile p) { // todo

    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(TextureManager.getTexture(balloonFilePrefix + layers), x, y);

        // just for testing
//         g.drawImage(TextureManager.getTexture("balloon"), x, y);
    }

    @Override
    public void update(float delta) {
        int[] pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
        double px = pointCoords[0];
        double py = pointCoords[1];
        if (this.x>px){
            //goLeft
            velX = -2;
        }
        else if (velX<px){
            //goRight
            velX = 2;
        }else{
            velX=0;
        }

        if (this.y > py){
            //go up
            velY = -2;
        }
        else if (this.y<py){
            //goDown
            velY=2;
        }else {
            velY=0;
        }

        if (this.x == px && this.y == py){
            currentPathPoint++;
        }

        this.x += velX;
        this.y += velY;

//        int[] pointCoords = App.currentGame.getLevel().getPathPoint(currentPathPoint);
//        double px = pointCoords[0] - x;
//        double py = pointCoords[1] - y;
//
//        double tanRes = Math.atan(py / px);
//        if(px == 0) {
//            tanRes = 90;
//        }
//
//        if (Double.isNaN(tanRes)) {
//            tanRes = 90;
//            System.out.println("nan");
//        }
//        double dX = Math.cos(tanRes) * speed * delta;
//        double dY = Math.sin(tanRes) *speed * delta;
//
//        if (dX > px || py > dY) {
//            x = pointCoords[0];
//            y = pointCoords[1];
//            currentPathPoint++;
//            return;
//        }
//
//        x += dX;
//        y += dY;


    }
}
