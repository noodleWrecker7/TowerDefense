package adtosh.towerdefense.turrets;

import adtosh.towerdefense.TextureManager;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class SuperMonkey extends BaseTurret {
    public SuperMonkey(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 700;
        this.projectileName = "magic ball";
        this.TimeTilSpawn = 0.2d;
    }

//    @Override
//    public Shape getBounds() {
//        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
//        return new Circle(x / 2, y / 2, radius / 2);
//    }
}
