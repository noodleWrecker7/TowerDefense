package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;

import java.lang.reflect.Field;

public class Upgrade implements  Upgradable{
    private String description;
    private int cost;


    private Upgradable upgradable;



    public Upgrade(String description, int cost) {
        this.description = description;
        this.cost = cost;
    }

    public void applyUpgrade(){
        App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney() - cost);
        upgradable.createUpgrade();

    }
    public void createUpgrade(Upgradable u){
        //this will class whatever you put into lamda
        this.upgradable = u;
    }

    @Override
    public void createUpgrade() {

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
