package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DartMonkey extends BaseTurret {

    public DartMonkey(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 250;
        this.power = 2;
        this.projectileName = "dart";
        this.TimeTilSpawn = 1.3d;

    }



}
