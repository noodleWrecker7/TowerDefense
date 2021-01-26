package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class Spike extends BaseTurret {
    private int lives;


    public Spike(double x, double y, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToSpikes(this);
        this.lives = 10;
    }

    @Override
    public Circle getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        Circle circle = new Circle(x/2, y/2, radius/2);
        return circle;
//        return new Circle(radius, x/2, y/2);
    }




    @Override
    public void update(float delta) {

    }

    public void handleBalloonCollision(){
        this.lives--;
        if (lives ==0){
            this.remove();
            App.currentGame.getLevel().removeFromSpikes(this);
        }

    }
}
;