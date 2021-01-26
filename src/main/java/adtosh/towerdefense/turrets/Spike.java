package adtosh.towerdefense.turrets;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class Spike extends BaseTurret {

    public Spike(double x, double y, String texture) {
        super(x, y, texture);
    }

    @Override
    public Circle getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        Circle circle = new Circle(x/2, y/2, radius/2);
        ScreenManager.addRoot("game.fxml", circle);
        return circle;
//        return new Circle(radius, x/2, y/2);
    }




    @Override
    public void update(float delta) {

    }
}
;