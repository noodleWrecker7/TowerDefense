package adtosh.towerdefense;

import adtosh.towerdefense.turrets.Turret;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;

public class Game {

    // todo scenebuilder, build game ui, eg tower select section, area for upgrades/stats, buy button, play controls, lives wave etc
    // todo with the canvas in the center of it all

    private Canvas canvas;
    private GraphicsContext context;
    private GraphicsContext backGround;



    // enum for states of game
    public enum GameState {PAUSED, NORMAL_SPEED, FAST_SPEED, ROUND_INACTIVE}

    // stores current GameState
    private GameState currentState = GameState.ROUND_INACTIVE;
    // map of all turrets currently in play, should all have unique id
    private HashMap<String, Turret> turrets;

    // standard new game from a level object
    public Game(Level level) {
        canvas =(Canvas) ScreenManager.getPane("game.fxml").lookup("#gameCanvas");
        // todo handle mouse input, maybe short cut keys?
//        canvas.addEventHandler();

    }

    // used to create new game from level and saveFile
    public Game(Level level, String saveFile) {
        this(level);
        // todo load save data
    }

    // load save data from file and edit game vars
    private void loadSaveData(String file) {

    }


    private long then;
    private AnimationTimer timer;
    private boolean running = true;

    public void init() { // starts timer loop, calls update() every frame
      this.canvas = new Canvas();
      this.context  = canvas.getGraphicsContext2D();

    }

    public void start(){
        init();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update((float) (now - then) / 1000000000f);
                    render(context);
                    then = (now);
                }

            }
        };
        timer.start();
    }

    // takes centre coordinates for a turret and tries to place, returns false if invalid, also selects turret,
    boolean placeTurret(float x, float y) {
        // todo also put in hashmap with unique id
        selectTurret(0, 0);
        return false;
    }

    // sets currentSelectedTurretID to id of turret, also returns id
    String selectTurret(float x, float y) {

        return null;
    }

    // called every frame, has render and update code
    void update(float delta) {
        System.out.println(delta);

    }

    // all rendering code goes in here
    public void render(GraphicsContext ctx) {

    }


}
