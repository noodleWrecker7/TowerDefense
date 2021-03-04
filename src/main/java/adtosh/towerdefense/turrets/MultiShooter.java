package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Dart;
import adtosh.towerdefense.turrets.BaseTurret;

public class MultiShooter extends BaseTurret {
    public MultiShooter(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 200;
        this.timeTilSpawn = 1.5d;
        this.power = 1;
        this.penetration =1;
        this.projectileName = "small dart";
        initialiseUpgrades();
    }

    @Override
    public void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 100);
        upgrade1.createUpgrade(() ->{ this.range +=50; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("large range, bigger bullets", 150);
        upgrade2.createUpgrade(() ->{
            this.range +=75;
            this.projectileName = "big dart";
        });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("increase fire rate", 100);
        upgrade3.createUpgrade(() -> this.timeSinceSpawn -= 0.3);
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("more power", 175);
        upgrade4.createUpgrade(() -> power ++);
        this.addUpgradeList2(upgrade4);

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



        if (timeSinceSpawn > timeTilSpawn){
            timeSinceSpawn = 0;
            for (int i = 0; i <360  ; i+= 45) {
                new Dart(x, y, i, power, penetration, this, projectileName);

            }

        }
    }




}
