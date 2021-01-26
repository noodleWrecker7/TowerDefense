package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Spike extends BaseTurret {

    public Spike(double x, double y, String texture) {
        super(x, y, texture);
    }

    @Override
    public Shape getBounds() {
        return null;
    }

    @Override
    public void update(float delta) {

    }
}
;