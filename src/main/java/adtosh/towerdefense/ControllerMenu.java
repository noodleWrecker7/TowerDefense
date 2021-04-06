package adtosh.towerdefense;

import adtosh.towerdefense.levels.Level;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControllerMenu {
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
}
