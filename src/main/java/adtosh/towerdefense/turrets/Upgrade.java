package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;

import java.lang.reflect.Field;

public class Upgrade {
    private String description;
    private int cost;
    private BaseTurret baseTurret;


    private Upgradable upgradable;



    public Upgrade(String description, int cost, BaseTurret baseTurret) {
        this.description = description;
        this.cost = cost;
        this.baseTurret = baseTurret;
    }

    public void applyUpgrade(){
        App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney() - cost);
        baseTurret.setValue(baseTurret.getValue()+cost);
        upgradable.createUpgrade();

    }
    public void createUpgrade(Upgradable u){
        //this will class whatever you put into lamda
        this.upgradable = u;

    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
