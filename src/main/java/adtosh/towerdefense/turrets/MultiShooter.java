package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Dart;

public class MultiShooter extends BaseTurret {
    public MultiShooter(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 200;
        this.timeTilSpawn = 2d;
        this.power = 1;
        this.penetration =1;
        this.projectileName = "small dart";
//        this.value = 300;
        initialiseUpgrades();
    }

    @Override
    public void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 100, this);
        upgrade1.createUpgrade(() ->{ this.range +=50; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("large range, bigger bullets", 150, this);
        upgrade2.createUpgrade(() ->{
            this.range +=75;
            this.projectileName = "big dart";
        });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("increase fire rate", 100, this);
        upgrade3.createUpgrade(() -> this.timeSinceSpawn -= 0.5);
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("more power", 175, this);
        upgrade4.createUpgrade(() -> power ++);
        this.addUpgradeList2(upgrade4);

    }

    @Override
    public void update(double delta) {
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
                new Dart(x, y, i, power, penetration, canPopLead, this, projectileName);

            }

        }
    }




}
