package adtosh.towerdefense.turrets;

public
class Wizard  extends BaseTurret implements ProjectileSplashes{

    private int splashDamageRange;
    public Wizard(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 300;
        this.projectileName = "magic ball";
        this.timeTilSpawn = 0.8d;

        this.power = 2;
        this.penetration = 0;
        this.canPopLead = true;

        this.splashDamageRange = 150;
        this.value=1200;

        initialiseUpgrades();

    }

    @Override
    protected void fire() {
        fire(x, y, angle, power, penetration, canPopLead, splashDamageRange, projectileName,  target);

    }

    @Override
    protected void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 600, this);
        upgrade1.createUpgrade(() ->{ this.range +=75; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("incredible range", 800, this);
        upgrade2.createUpgrade(() ->{ this.range +=75; });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("shoot fast", 1000, this);
        upgrade3.createUpgrade(() -> {
            this.timeTilSpawn -=0.15;
        });
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("more power", 1200, this);
        upgrade4.createUpgrade(() -> power ++);
        this.addUpgradeList2(upgrade4);

    }


}
