package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import javafx.scene.shape.Circle;

import java.util.Iterator;

public class Spike extends BaseTurret {
    private int lives;
    private String texturePrefix = "spikes-";


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
        return new Circle(x / 2, y / 2, radius / 2);

    }

    @Override
    public void update(float delta) {
    }

    public void handleBalloonCollision(Iterator<Spike> iterator) {
        this.lives--;
        if (lives == 0) {
            this.remove(iterator);
        }
        this.textureName = texturePrefix + lives;

    }


}
;