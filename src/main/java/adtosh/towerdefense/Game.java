package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Iterator;

public class Game {

    // todo scenebuilder, build game ui, eg tower select section, area for upgrades/stats, buy button, play controls, lives wave etc
    // todo with the canvas in the center of it all

    private Canvas canvas;
    private GraphicsContext context;
    private Level level;

    // enum for states of game
    public enum GameState {

        PAUSED{
            @Override
            public void createFunction() {

            }
        },
        NORMAL_SPEED{
            @Override
            public void createFunction() {
//                for (BaseTurret baseTurret : App.currentGame.getLevel().getTurrets()){
//                    baseTurret.setTimeTilSpawn(baseTurret.getTimeTilSpawn()/2);
//                }

                //balloons are sped up unside of balloon class. is this bad practice?
                }
        },
        FAST_SPEED{
            @Override
            public void createFunction() {

            }
        },
        ROUND_INACTIVE{
            @Override
            public void createFunction() {
                //nothing so far

            }
        };



        public abstract void createFunction();

    }

    private GameState currentState = GameState.ROUND_INACTIVE;

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState state) {
//        if (this.currentState == state)return;
        this.currentState = state;
//        this.currentState.createFunction();
    }

    public void resume() {
        currentState = GameState.NORMAL_SPEED;
        currentState.createFunction();
        //todo change remove
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
        //times by 2?
//        double xn = Math.max(rect.getX(), Math.min(circle.getCenterX(), rect.getX()+rect.getWidth()));
//        double yn = Math.max(rect.getY()+ rect.getHeight(), Math.min(circle.getCenterY(), rect.getY()));
//
//        double dx = xn - circle.getCenterX();
//        double dy = yn - circle.getCenterY();
//
////        return Math.sqrt(dx*dx + dy*dy) <= circle.getRadius();
//
//        if(dx * dx + dy *dy <= circle.getRadius()*circle.getRadius()){
//            System.out.println("COLLISION");
//
//            return true;
//        }
//        return false;



//        return (dx*dx + dy*dy) <= circle.getRadius()*circle.getRadius();

        double [] pointsX = {rect.getX(), rect.getX()+rect.getWidth()};
        double [] pointsY = {rect.getY(), rect.getY()+rect.getHeight()};

        for (int x = 0; x <2 ; x++) {
            for (int y = 0; y <2 ; y++) {
                double dx = pointsX[x] - circle.getCenterX();
                double dy = pointsY[y] - circle.getCenterY();
                if (Math.sqrt(dx * dx + dy * dy) < circle.getRadius()){
                    return true;
                }

            }

        }
        return false;





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

                    update((float) (now - then) / 1000000000f);
                    //difference in time of excecuting one loop after the other // 10 x 10^9
                    render();
                    then = (now);


            }
        };
        timer.start();
    }





    void update(float delta) {
        level.update(delta);


    }



    public void render() {



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
        for (Projectile projectile: level.getHitProjectiles()){
            projectile.render(context);


        }


        context.setFill(Color.BLACK);
        context.setFont(new Font(45));
        context.fillText("$" + level.getMoney(), 30, 34);

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
