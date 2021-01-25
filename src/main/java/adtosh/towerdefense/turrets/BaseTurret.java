package adtosh.towerdefense.turrets;

import adtosh.towerdefense.entity.Entity;

public abstract class BaseTurret extends Entity {
    private boolean pickedUp = false;

    public BaseTurret(double x, double y) {
        super(x, y);
    }

    public BaseTurret() {
        super();
    }


    public boolean isClicked() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
