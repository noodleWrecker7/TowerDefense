package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MultiShooter extends BaseTurret {
    public MultiShooter(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 200;
        this.projectileName = "no target dart";
        this.TimeTilSpawn = 1.5d;
        this.power = 5;
    }

    @Override
    public void update(float delta) {
        if(!isPlaced) return;

        timeSinceSpawn += delta;


        for (Balloon balloon: App.currentGame.getLevel().getBalloons()){
            if (App.currentGame.collides(this.getRangeBounds(), balloon.getBounds())){
                if (timeSinceSpawn > TimeTilSpawn) {
                    timeSinceSpawn = 0;
                    try {




                        for (int i = 0; i <360  ; i+= 45) {
                            System.out.println("fire");
                            Constructor<? extends Projectile> constructor = App.currentGame.getLevel().getProjectileConstructors().get(projectileName);
                            Projectile projectile = constructor.newInstance(x, y, i, power, "dart");

                        }
//                        Constructor<? extends Projectile> constructor = App.currentGame.getLevel().getProjectileConstructors().get(projectileName);
//                        Projectile projectile = constructor.newInstance(x, y, angle, power, "dart");


                    } catch ( InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }


            }
        }

    }


}
