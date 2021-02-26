package adtosh.towerdefense.turrets;

import adtosh.towerdefense.Placeable;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Wizard  extends BaseTurret {
    public Wizard(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 300;
        this.projectileName = "magic ball";
        this.TimeTilSpawn = 0.8d;

        this.power = 2;

    }







}