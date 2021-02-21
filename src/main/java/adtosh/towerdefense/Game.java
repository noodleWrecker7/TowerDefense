package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
//    private ArrayList<BaseTurret> turrets = new ArrayList<>();


//    public void addToTurrets(BaseTurret t){
//        turrets.add(t);
//    }

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

    public boolean collides(Circle circle, Rectangle rect) {
        //find nearest point of rectangle
        double xn = Math.max(rect.getX(), Math.min(circle.getCenterX(), rect.getX()+rect.getWidth()));
        double yn = Math.max(rect.getY()+ rect.getHeight(), Math.min(circle.getCenterY(), rect.getY()));

        double dx = xn - circle.getCenterX();
        double dy = yn - circle.getCenterY();

        return (dx*dx + dy*dy) <= circle.getRadius()*circle.getRadius();


//        double closestX = clamp(c1.getCenterX(), r1.getX(), r1.getX() + r1.getWidth());
//        double closestY = clamp(c1.getCenterY() , r1.getY() - r1.getHeight(), r1.getY());
//
//        double distanceX = c1.getCenterX()- closestX;
//        double distanceY = c1.getCenterY()  - closestY;
//
//        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(c1.getRadius(), 2);
    }

    private double clamp(double value, double min, double max) {
        double x = value;
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        return x;
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
        level.initialisePath();
        level.addProjectilesType();
    }

    public void start() {
        init();
        then = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update((float) (now - then) / 1000000000f);
                    //difference in time of excecuting one loop after the other // 10 x 10^9
                    render();
                    then = (now);
                }

            }
        };
        timer.start();
    }





    void update(float delta) {
        level.update(delta);
//        checkCollision();

    }

    private void checkCollision() {
        Iterator<Balloon> balloonIterator = level.getBalloons().iterator();
        while (balloonIterator.hasNext()) {
            Balloon balloon = balloonIterator.next();



        }

    }

    public void render() {
//        System.out.println(level.getTurrets().size());


//        this.context = canvas.getGraphicsContext2D();
        level.render(context);
        for (Balloon balloon : level.getBalloons()) {
            balloon.render(context);
        }
        for (Spike spike : level.getSpikes()) {
            spike.render(context);
        }

        for (BaseTurret t: level.getTurrets()) {
            t.render(context);
        }

        for(Projectile projectile: level.getProjectiles()){
            projectile.render(context);
        }

//        for (Entity entity : entities) {
//            entity.render(context);
//        }


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


}
