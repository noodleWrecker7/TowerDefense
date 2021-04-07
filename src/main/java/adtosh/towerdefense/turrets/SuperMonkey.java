package adtosh.towerdefense.turrets;

public class SuperMonkey extends BaseTurret implements ProjectileSplashes {

    private int splashDamageRange;
    public SuperMonkey(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 500;
        this.projectileName = "dart";
        this.timeTilSpawn = 0.2d;
        this.power = 3;
        this.penetration = 0;
        this.canPopLead = false;
        this.splashDamageRange = 50;
//        this.value = 2500;

        initialiseUpgrades();


    }

    @Override
    protected void initialiseUpgrades() {

        Upgrade upgrade1 = new Upgrade("increase range", 1500, this);
        upgrade1.createUpgrade(() ->{ this.range +=100; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("incredible range", 1800, this);
        upgrade2.createUpgrade(() ->{ this.range +=100; });
        this.addUpgradeList1(upgrade2);

        Upgrade upgrade3 = new Upgrade("can pop lead", 1000, this);
        upgrade3.createUpgrade(() -> {
            this.canPopLead = true;
            this.projectileName="magic ball";
        });
        this.addUpgradeList2(upgrade3);

        Upgrade upgrade4 = new Upgrade("more power", 1500, this);
        upgrade4.createUpgrade(() -> power ++);
        this.addUpgradeList2(upgrade4);

    }

    @Override
    protected void fire() {
        if (this.projectileName .equals("dart")) {
            super.fire();
        }else {
            fire(x, y, angle, power, penetration, canPopLead, splashDamageRange, projectileName,  target);


        }
    }

    //    @Override
//    public Shape getBounds() {
//        double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
//        return new Circle(x / 2, y / 2, radius / 2);
//    }
}
