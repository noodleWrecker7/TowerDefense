package adtosh.towerdefense;


import adtosh.towerdefense.levels.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

// main controller class for all fxml files to go through
public class Controller {

    @FXML
    public void loadLevel1(MouseEvent event) {
        System.out.println("Here");

        // loads level and starts gameplay
        Level level = new Level();
        level.loadPath(0);
        App.currentGame = new Game(level);
        App.currentGame.start();

        // then shows it
        ScreenManager.activate("game.fxml");
    }

    @FXML
    public void loadLevel2(MouseEvent mouseEvent) {
    }

    @FXML
    public void loadLevel3(ActionEvent event) {

    }
}
