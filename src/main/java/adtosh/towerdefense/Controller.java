package adtosh.towerdefense;


import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.Spike;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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

    private void  loadLevel(int levelID){
        if(App.currentGame == null) {
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
        Spike spike = new Spike(event.getSceneX()*2, event.getSceneY()*2);
        Game.setMouseMoved(spike);


    }
}
