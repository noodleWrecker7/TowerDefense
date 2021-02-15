package adtosh.towerdefense;


import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.entity.projectiles.Wizard;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.DartMonkey;
import adtosh.towerdefense.turrets.Spike;
import adtosh.towerdefense.turrets.SuperMonkey;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

// main controller class for all fxml files to go through
public class Controller {

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
//        if (!App.currentGame.getLevel().isCarryingItem()) {
//            App.currentGame.getLevel().setCarryingItem(true);
//            Spike spike = new Spike(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
//            spike.setMouseMoveListener();
//        }
        if (canBuy(event)) {
            Spike spike = new Spike(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
            spike.setMouseMoveListener();

        }

    }

    public void buyMonkey(MouseEvent event) {
//        if (canBuy())
//        if (!App.currentGame.getLevel().isCarryingItem()) {
//            App.currentGame.getLevel().setCarryingItem(true);
//            DartMonkey m = new DartMonkey(event.getSceneX() * 2, event.getSceneY() * 2, "spikes-11");
//            m.setMouseMoveListener();
//        }
    }

    public boolean canBuy(MouseEvent event) {
        if (!App.currentGame.getLevel().isCarryingItem()) {
            App.currentGame.getLevel().setCarryingItem(true);
            return true;
        }
        return false;


    }

    private void buyDefense() {

    }

    public void quitToMenu() {

    }

    public void buyWizard(MouseEvent event) {
        // get x is relative to node the event happened and scence x is relative to the scene
        if (canBuy(event)) {
            Wizard wizard = new Wizard(event.getSceneX() * 2, event.getSceneY() * 2, "wizard");
            wizard.setMouseMoveListener();
        }
    }

    public void buySuperMonkey(MouseEvent event) {
        if (canBuy(event)) {
            SuperMonkey monkey = new SuperMonkey(event.getSceneX() * 2, event.getSceneY() * 2, "super monkey");
            monkey.setMouseMoveListener();
        }


    }

    public void quitToMenu(MouseEvent event) {
    }
}
