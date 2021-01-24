package adtosh.towerdefense.turrets;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class Spike extends Entity {

    public Spike(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(GraphicsContext g) {
        System.out.println( x);
        g.drawImage(TextureManager.getTexture("spike"), x, y);

    }

    @Override
    public void update(float delta) {

    }
}
