package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Spike extends BaseTurret {

    public Spike(double x, double y, Image texture) {
        super(x, y, texture);
    }

    public Spike() {
        super();
    }

    @Override
    public void render(GraphicsContext g) {
        if (x< App.currentGame.getCanvas().getWidth() *2 - TextureManager.getTexture("spike").getWidth()) {
            g.drawImage(TextureManager.getTexture("spike"), x, y);
        }

    }

    @Override
    public void update(float delta) {

    }
}
;