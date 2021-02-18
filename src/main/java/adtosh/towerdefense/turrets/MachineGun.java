package adtosh.towerdefense.turrets;

public class MachineGun extends BaseTurret {

    public MachineGun(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 500;
        this.projectileName = "dart";
        this.TimeTilSpawn = 0.05d;
        this.correctiveAngle = -45;
        this.power = 1;

    }


}
