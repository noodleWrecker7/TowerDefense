package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.Placeable;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.awt.font.TextMeasurer;

public class Spike extends Entity implements Placeable {
    private int lives;
    private String texturePrefix = "spikes-";

    //todo make these part of an interface
    private double range;
    private boolean isPlaced;

    public  static int price;


    public Spike(double x, double y, String texture) {
        super(x, y, texture);
        //todo WARNING: the code below can cause a duplicate if we are adding a "new Sike" to an array of spikes
        App.currentGame.getLevel().addToSpikes(this);
        this.lives = 11;
        this.range = TextureManager.getTexture(this.textureName).getWidth() / 2;

    }

    @Override
    public Circle getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        if (isPlaced) {
            return new Circle(x / 2, y / 2, radius / 1.5);
        }else {
            return new Circle(x / 2, y / 2, radius / 6);


        }

}

    @Override
    public void update(double delta) {
    }



    public void handleBalloonCollision() {
        this.lives--;
        this.textureName = texturePrefix + lives;


   }

   public int getLives(){
        return lives;
   }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

//    @Override
//    public void setMouseMoveListener() {
//            Canvas canvas = App.currentGame.getCanvas();
//            canvas.setOnMouseMoved(this::handleMouseMove);
//            canvas.setOnMouseClicked(this::handleMouseClick);
//        is this the best way
//
//    }

    @Override
    public void handleMouseMove(MouseEvent event) {
        if (isPlaced) return;
        y = (event.getY() * 2);
        x = (event.getX() * 2);

    }

    @Override
    public void handleMouseClick(MouseEvent event) {
        if(isPlaced) return;
        for (Line line : App.currentGame.getLevel().getPath()) {
            if (this.getBounds().intersects(line.getLayoutBounds())) {
                App.currentGame.getCanvas().setOnMouseMoved(e -> { });
                App.currentGame.getCanvas().setOnMouseClicked(App.currentGame.getLevel()::selectTurret);
                isPlaced = true;
                App.currentGame.getLevel().setCarryingItem(false);
                break;
            }
        }

    }

}
;