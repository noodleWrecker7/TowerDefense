package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Projectile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface ProjectileSplashes {

    default void fire(double x, double y, double angle, int power, int penetration, boolean canPopLead, int splashDamageRange, String projectileName, Balloon target) {
        try {
            Constructor<? extends Projectile> constructor = App.currentGame.getLevel().getProjectileConstructors().get(projectileName);
            constructor.newInstance(x, y, angle, power, penetration, canPopLead, splashDamageRange, projectileName,  target);


        } catch ( InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
