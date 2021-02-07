package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DartMonkey extends BaseTurret {

    public DartMonkey(double x, double y, String texture) {
        super(x, y, texture);
        range = 50f;
        System.out.println("yo");
        App.currentGame.getLevel().addToTurrets(this);
    }

    @Override
    public Shape getBounds() {
        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
        return new Circle(x/2, y/2, radius/2);
    }

    @Override
    public void update(float delta) {

    }
}
