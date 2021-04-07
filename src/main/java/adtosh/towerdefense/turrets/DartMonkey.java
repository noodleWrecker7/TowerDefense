package adtosh.towerdefense.turrets;

public class DartMonkey extends BaseTurret {

    public DartMonkey(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 250;
        this.power = 2;
        this.projectileName = "dart";
        this.timeTilSpawn = 1.3d;
        this.penetration =0;
        this.canPopLead = false;
//        this.value = 50;
        initialiseUpgrades();



    }

    @Override
    protected void initialiseUpgrades() {
        Upgrade upgrade = new Upgrade("increase range ", 40, this);
        upgrade.createUpgrade(() -> this.range +=50 );
        this.addUpgradeList1(upgrade);

        Upgrade upgrade2 = new Upgrade("unlock extra range", 80, this);
        upgrade2.createUpgrade(() -> this.range +=50);
        this.addUpgradeList1(upgrade2);

        //branch 2



        Upgrade upgrade3 = new Upgrade("more power", 150, this);
        upgrade3.createUpgrade(() ->{ power ++; });
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("shoot bigger, penetrative darts", 150, this);
        upgrade4.createUpgrade(() -> {
            this.projectileName = "big dart";
            this.penetration ++;


        });
        this.addUpgradeList2(upgrade4);


    }
}
