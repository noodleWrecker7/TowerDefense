package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Game {

    // todo scenebuilder, build game ui, eg tower select section, area for upgrades/stats, buy button, play controls, lives wave etc
    // todo with the canvas in the center of it all

    private Canvas canvas;
    private GraphicsContext context;

    private Level level;


    // enum for states of game
    public enum GameState {
        PAUSED, NORMAL_SPEED, FAST_SPEED, ROUND_INACTIVE
    }
    //STORES WHAT PHASE THE ACTUAL GAME IS IN

    // stores current GameState
    private GameState currentState = GameState.ROUND_INACTIVE;
    // map of all turrets currently in play, should all have unique id
    private HashMap<String, BaseTurret> turrets;
    private ArrayList<Entity> entities = new ArrayList<>();

    // standard new game from a level object

//    private Image bg;

    public void resume() {
        currentState = GameState.NORMAL_SPEED;
    }

    public Game(Level _level) {

        canvas = (Canvas) ScreenManager.getPane("game.fxml").lookup("#gameCanvas");
        context = canvas.getGraphicsContext2D();
        context.scale(.5, .5);
        level = _level;
        loadSaveData("level" + level.getLevelID());
        // todo dynamic scaling to window size
        // todo handle mouse input, maybe short cut keys?
//        canvas.addEventHandler();
        // todo get background image from level data


    }

    // used to create new game from level and saveFile
    public Game(Level level, String saveFile) {
        this(level);
        // todo load save data
    }


    // load save data from file and edit game vars
    private void loadSaveData(String file) {
//        level.addBalloons();
        //todo hardcoded for now only


    }


    private long then;
    private AnimationTimer timer;
    private boolean running = true;


    // todo can probably remove this
    public void init() { // starts timer loop, calls update() every frame
        level.initialisePath();
    }

    public void start() {
        init();
        then = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update((float) (now - then) / 1000000000f);
                    render();
                    then = (now);
                }

            }
        };
        timer.start();
    }

    public void addToEntity(Entity entity) {
        this.entities.add(entity);
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
        level.update(delta);
        checkCollision();

    }

    private void checkCollision() {
        Iterator<Balloon> balloonIterator = level.getBalloons().iterator();
        while (balloonIterator.hasNext()){
            Balloon balloon = balloonIterator.next();

            Iterator<Spike> spikeIterator = level.getSpikes().iterator();
            while (spikeIterator.hasNext()){
                Spike spike = spikeIterator.next();

                if (balloon.getBounds().intersects(spike.getBounds().getLayoutBounds())){
//                    System.out.println("HERE");
                    spike.handleBalloonCollision(spikeIterator);
                    balloon.handleDefenseCollision(balloonIterator);
                }

            }

        }
//        for (Balloon balloon: level.getBalloons()){
//            for (Spike spike: level.getSpikes()){
//                if (balloon.getBounds().intersects(spike.getBounds().getLayoutBounds())){
//                    System.out.println("HERE");
////                    spike.handleBalloonCollision();
//                    balloon.handleDefenseCollision();
//                }
//            }
//        }
//        for (Entity entity : entities) {
//            for (Entity entity1 : entities) {
//                if (entity instanceof Balloon) {
//                    if (entity1 instanceof Projectile || entity1 instanceof Spike) {
//                        if (entity.getBounds().intersects(entity1.getBounds().getLayoutBounds())){
//                            if (((Spike)entity1).isPlaced()) {
//                                System.out.println("OKAY OKAY");
//                            }
//
//                        }
//                    }
//
//                }
//            }
//        }
    }

    public void render() {
//        this.context = canvas.getGraphicsContext2D();
        level.render(context);

        for (Entity entity : entities) {
            entity.render(context);
        }


    }

    public Level getLevel() {
        return level;
    }

    public void takeLives(int lives) {
        level.setLives(level.getLives() - lives);
        ;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void removeFromEntity(Entity e){
        this.entities.remove(e);

    }
}
