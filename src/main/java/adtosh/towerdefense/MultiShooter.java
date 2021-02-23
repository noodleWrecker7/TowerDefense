package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Dart;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class MultiShooter extends BaseTurret {
    public MultiShooter(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 200;
//        this.projectileName = "DART";
        this.TimeTilSpawn = 1.5d;
        this.power = 5;
    }

    @Override
    public void update(float delta) {
        if(!isPlaced) return;

        timeSinceSpawn += delta;

        boolean collides = false;



        for (Balloon balloon: App.currentGame.getLevel().getBalloons()) {

            if (App.currentGame.collides(getRangeBounds(), balloon.getBounds())) {
                collides = true;
                break;
            }
        }
        if (!collides)return;



        if (timeSinceSpawn > TimeTilSpawn){
            timeSinceSpawn = 0;
            for (int i = 0; i <360  ; i+= 45) {
                Dart dart = new Dart(x, y, i, power, "dart");
            }

        }
    }




}
