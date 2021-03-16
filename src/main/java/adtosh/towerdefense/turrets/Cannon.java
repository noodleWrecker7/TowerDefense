package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.projectiles.Projectile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Cannon extends BaseTurret {

    private int splashDamageRange;
    public Cannon(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 300;
        this.projectileName = "missile";
        this.timeTilSpawn = 1d;
        this.power = 4;
        this.penetration = 0;
        this.canPopLead = true;
        this.splashDamageRange = 115;
        initialiseUpgrades();

    }

    @Override
    protected void fire() {
        try {
            Constructor<? extends Projectile> constructor = App.currentGame.getLevel().getProjectileConstructors().get(projectileName);
            constructor.newInstance(x, y, angle, power, penetration, canPopLead, splashDamageRange, projectileName, target);


        } catch ( InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 600);
        upgrade1.createUpgrade(() ->{ this.range +=60; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("incredible range", 800);
        upgrade2.createUpgrade(() ->{ this.range +=90; });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("increase splash damage range", 600);
        upgrade3.createUpgrade(() -> this.splashDamageRange += 20);
        this.addUpgradeList2(upgrade3);



        Upgrade upgrade4 = new Upgrade("more power", 900);
        upgrade4.createUpgrade(() -> power ++);
        this.addUpgradeList2(upgrade4);


    }
}
