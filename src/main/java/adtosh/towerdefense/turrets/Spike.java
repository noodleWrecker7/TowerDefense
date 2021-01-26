package adtosh.towerdefense.turrets;

import adtosh.towerdefense.TextureManager;
import javafx.scene.shape.Circle;

public class Spike extends BaseTurret {

    public Spike(double x, double y, String texture) {
        super(x, y, texture);
    }

    @Override
    public Circle getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;

        return new Circle(radius);
    }

    @Override
    public void update(float delta) {

    }
}
;