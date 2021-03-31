package adtosh.towerdefense;


import adtosh.towerdefense.turrets.*;
import adtosh.towerdefense.levels.Level;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

// main controller class for all fxml files to go through
public class Controller {

    @FXML
    public void loadLevel1(MouseEvent event) {
        loadLevel(0);

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
//        if (App.currentGame == null) {
//            Level level = new Level(levelID);
//            level.loadPath(levelID);
//            App.currentGame = new Game(level);
//            App.currentGame.start();
//        } else {
//            App.currentGame.resume();
//        }


        Level level = new Level(levelID);
        level.loadPath(levelID);
        App.currentGame = new Game(level);
        App.currentGame.start();
        //because there a null check when i press again  it only resumes ()

        ScreenManager.activate("game.fxml");
        App.currentGame.resumeScaling();

    }

    @FXML
    private void fastForward() {
        App.currentGame.setCurrentState(Game.GameState.FAST_SPEED);
    }

    public void buySpike(MouseEvent event) {
        int price = 50;

        if (!App.currentGame.getLevel().isCarryingItem()) {
            if (price <= App.currentGame.getLevel().getMoney()) {
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


    private <T extends BaseTurret> void canBuy(MouseEvent event, Class<T> clazz, String texture, int price) {


        if (!App.currentGame.getLevel().isCarryingItem()) {


            try {
                if (price <= App.currentGame.getLevel().getMoney()) {
                    App.currentGame.getLevel().setCarryingItem(true);
                    BaseTurret baseTurret = clazz.getConstructor(double.class, double.class, String.class).newInstance(event.getSceneX() * 2, event.getSceneY() * 2, texture);
                    baseTurret.setMouseMoveListener();
                    App.currentGame.getLevel().setMoney(App.currentGame.getLevel().getMoney() - price);

                }
            } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }


    public void quitToMenu() {
        App.currentGame.returnToMenu();
//        App.currentGame.setRunning(false);
//        reset();
//        ScreenManager.activate("menu.fxml");


    }

    private void reset() {

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


    public void buyMachineGun(MouseEvent event) {
        canBuy(event, MachineGun.class, "machine gun", 2000);


    }


    public void buyMultiShooter(MouseEvent event) {
        canBuy(event, MultiShooter.class, "multi shooter", 300);


    }

    public void buyDartMonkey(MouseEvent event) {
        canBuy(event, DartMonkey.class, "dart monkey", 50);


    }

    public void buyCannon(MouseEvent event) {
        canBuy(event, Cannon.class, "cannon", 800);


    }


    public void upgrade1() {
        BaseTurret turret = App.currentGame.getLevel().getSelectedTurret();
        if (turret != null && turret.getUpgradeNumber1() < turret.getUpgradeList1().size()) {
            if (turret.getCurrentUpgrade1().getCost() <= App.currentGame.getLevel().getMoney()) {

                turret.applyUpgrade1();
            }
        }
    }

    public void upgrade2() {
        BaseTurret turret = App.currentGame.getLevel().getSelectedTurret();
        if (turret != null && turret.getUpgradeNumber2() < turret.getUpgradeList2().size()) {
            if (turret.getCurrentUpgrade2().getCost() <= App.currentGame.getLevel().getMoney()) {
                turret.applyUpgrade2();
            }


        }
    }

    public void startRound() {
//        if (App.currentGame.getLevel().isWaveOnGoing()) return;

        if (App.currentGame.getCurrentState() != Game.GameState.ROUND_INACTIVE) return;


        String line;
        ArrayList<String[]> lines = new ArrayList<>();

        try {
            String fileName = "wave-" + App.currentGame.getLevel().getWave() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {

                String[] words = line.split(" ");
                lines.add(words);

            }
            reader.close();

            App.currentGame.setCurrentState(Game.GameState.NORMAL_SPEED);
            App.currentGame.getLevel().setBalloonsSpawnQueue(lines);
//            App.currentGame.getLevel().setWaveOnGoing(true);


        } catch (IOException e) {
            e.printStackTrace();
        }


//        App.currentGame.getLevel().createBalloons(lines);
//        App.currentGame.getLevel().setBalloonsSpawnQueue(lines);
//        App.currentGame.getLevel().setWaveOnGoing(true);


    }


//    public void quitToMenu(MouseEvent event) {
//        ScreenManager.activate("game.fxml");
//    }

    public void normalSpeed() {
        App.currentGame.setCurrentState(Game.GameState.NORMAL_SPEED);
    }

    private Game.GameState stateBeforePause;

    public void pause() {
        Game.GameState state = App.currentGame.getCurrentState();

        if (state != Game.GameState.PAUSED) {
            this.stateBeforePause = state;
            App.currentGame.setCurrentState(Game.GameState.PAUSED);

        } else {
            App.currentGame.setCurrentState(stateBeforePause);

        }

    }

    public void sell(MouseEvent event) {
        BaseTurret turretToSell = App.currentGame.getLevel().getSelectedTurret();
        if (turretToSell == null) return;
        Level currentLevel = App.currentGame.getLevel();
        currentLevel.setMoney(currentLevel.getMoney() + turretToSell.getValue());
        App.currentGame.getLevel().getTurrets().remove(turretToSell);
    }
}
