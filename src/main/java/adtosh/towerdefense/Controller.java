package adtosh.towerdefense;


import adtosh.towerdefense.turrets.*;
import adtosh.towerdefense.levels.Level;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

// main controller class for all fxml files to go through
public class Controller   {

    @FXML
    public void loadLevel1(MouseEvent event) {
        loadLevel(0);

        //       loads level and starts gameplay
//        Level level = new Level();
//        level.loadPath(0);
//        App.currentGame = new Game(level);
//        App.currentGame.start();
//
//        // then shows it
//        ScreenManager.activate("game.fxml");
    }

    @FXML
    public void loadLevel2(MouseEvent mouseEvent) {
        loadLevel(1);

    }

    @FXML
    public void loadLevel3(MouseEvent event) {

    }

    @FXML
    public void loadLevel4(MouseEvent event) {
    }

    private void loadLevel(int levelID) {
        if (App.currentGame == null) {
            Level level = new Level(levelID);
            level.loadPath(levelID);
            App.currentGame = new Game(level);
            App.currentGame.start();
        } else {
            App.currentGame.resume();
        }

        // then shows it
        ScreenManager.activate("game.fxml");

    }

    public void buySpike(MouseEvent event) {
        int price = 50;

        if (!App.currentGame.getLevel().isCarryingItem()) {
            if (price<= App.currentGame.getLevel().getMoney()){
                App.currentGame.getLevel().setCarryingItem(true);
                Spike spike = new Spike(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
                spike.setMouseMoveListener();
                App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney() - price);
        }
        }


//        if (!App.currentGame.getLevel().isCarryingItem()) {
//            App.currentGame.getLevel().setCarryingItem(true);
//            Spike spike = new Spike(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
//            spike.setMouseMoveListener();
//        }
//        if (canBuy(event,Spike.class, "spikes-11")) {
////            Spike spike = new Spike(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
////            spike.setMouseMoveListener();
//
//        }

    }



    private  <T extends BaseTurret> void canBuy(MouseEvent event, Class <T>clazz, String texture, int price) {


        if (!App.currentGame.getLevel().isCarryingItem()) {



            try {
                if (price<= App.currentGame.getLevel().getMoney()){
                    App.currentGame.getLevel().setCarryingItem(true);
                    BaseTurret baseTurret = clazz.getConstructor(double.class, double.class, String.class).newInstance(event.getSceneX() * 2, event.getSceneY() * 2, texture);
                    baseTurret.setMouseMoveListener();
                    App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney() - price);

                }
            } catch ( IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }



    }



    public void quitToMenu() {

    }

    public void buyWizard(MouseEvent event) {
        canBuy(event, Wizard.class, "wizard", 1200);



}

    public void buySuperMonkey(MouseEvent event) {
       canBuy(event, SuperMonkey.class, "super monkey", 2500);




    }
    public void buySpikeMachine(MouseEvent event) {
        canBuy(event, SpikeMachine.class, "spike machine", 1500);

        }




    public void buyMachineGun(MouseEvent event){
        canBuy(event , MachineGun.class, "machine gun", 2000);


    }


    public void buyMultiShooter(MouseEvent event){
        canBuy(event, MultiShooter.class, "multi shooter", 300);




    }

    public void buyDartMonkey(MouseEvent event){
        canBuy(event, DartMonkey.class, "dart monkey", 50);



    }

    public void buyCannon(MouseEvent event)  {
        canBuy(event, Cannon.class, "cannon", 800);



    }





    public void upgrade1(){
        BaseTurret turret = App.currentGame.getLevel().getSelectedTurret();
        if (turret != null && turret.getUpgradeNumber1()<turret.getUpgradeList1().size()){
            turret.applyUpgrade1();

        }
    }

    public void upgrade2(){
        BaseTurret turret = App.currentGame.getLevel().getSelectedTurret();
        if (turret != null  && turret.getUpgradeNumber2()<turret.getUpgradeList2().size()){
            turret.applyUpgrade2();


        }
    }

    public void startRound()  {
        System.out.println(App.currentGame.getLevel().isWaveOnGoing());
        if (App.currentGame.getLevel().isWaveOnGoing()) return;
        System.out.println("HERE");
        String line;
        ArrayList<String[]> lines = new ArrayList<>();

        try {


            BufferedReader reader = new BufferedReader(new FileReader("wave.txt"));
            while ((line = reader.readLine()) != null) {
                String [] words = line.split(" ");
                lines.add(words);

            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        App.currentGame.getLevel().createBalloons(lines);
        App.currentGame.getLevel().setWaveOnGoing(true);


    }


    public void quitToMenu(MouseEvent event) {
    }
}
