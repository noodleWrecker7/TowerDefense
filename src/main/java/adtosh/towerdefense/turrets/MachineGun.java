package adtosh.towerdefense.turrets;

public class MachineGun extends BaseTurret {

    public MachineGun(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 370;
        this.projectileName = "dart";
        this.timeTilSpawn = 0.17d;
//        this.timeTilSpawn = 1d;
        this.correctiveAngle = -45;
        this.power = 1;
        this.penetration = 0;
        this.canPopLead = false;
//        this.value = 2000;
        initialiseUpgrades();


    }

    @Override
    protected void initialiseUpgrades() {

        Upgrade upgrade1 = new Upgrade("increase range", 600, this);
        upgrade1.createUpgrade(() ->{ this.range +=80; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("get incredible range", 1500, this);
        upgrade2.createUpgrade(() ->{ this.range += 100; });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("shoot faster", 1100, this);
        upgrade3.createUpgrade(() ->{ this.timeTilSpawn -= 0.1; });
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("increase power", 1500, this);
        upgrade4.createUpgrade(() ->{ this.power += 1; });
        this.addUpgradeList2(upgrade4);

    }
}
