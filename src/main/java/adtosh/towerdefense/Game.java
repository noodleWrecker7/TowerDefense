package adtosh.towerdefense;

import adtosh.towerdefense.levels.Level;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Game {

    // todo scenebuilder, build game ui, eg tower select section, area for upgrades/stats, buy button, play controls, lives wave etc
    // todo with the canvas in the center of it all

    private Canvas canvas;
    private GraphicsContext context;
    private Level level;


    // enum for states of game
    public enum GameState {

        PAUSED,
        NORMAL_SPEED,
        FAST_SPEED,
        ROUND_INACTIVE;

    }

    private GameState currentState = GameState.ROUND_INACTIVE;

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;

    }

    public void resume() {
        currentState = GameState.NORMAL_SPEED;
    }

    public  Game(Level _level) {

        canvas = (Canvas) ScreenManager.getPane("game.fxml").lookup("#gameCanvas");
        context = canvas.getGraphicsContext2D();
        context.save();
        context.scale(0.5, 0.5);

        level = _level;
        loadSaveData("level" + level.getLevelID());



//        scale = new Scale(0, 0, 0, 0);
//        ScreenManager.getPane("game.fxml").getTransforms().add(scale);
//        resumeScaling();

        // todo dynamic scaling to window size
        // todo handle mouse input, maybe short cut keys?
//        canvas.addEventHandler();
        // todo get background image from level data


    }

    private Scale scale;

    public void resumeScaling(){

        Pane root= ScreenManager.getPane("game.fxml");

        scale = new Scale(0, 0, 0, 0);
        scale.xProperty().bind(root.widthProperty().divide(root.getPrefWidth()));
        scale.yProperty().bind(root.heightProperty().divide(root.getPrefHeight()));
        root.getTransforms().add(scale);

    }

    public void pauseScaling(){
        Pane root= ScreenManager.getPane("game.fxml");
        root.getTransforms().remove(scale);

    }


    public void returnToMenu(){
        App.currentGame.setRunning(false);
        reset();
        ScreenManager.activate("menu.fxml");
    }

    private void reset(){
        //todo create reset method
        context.restore(); //pops call to save so this is why we can call it multiple times
        this.canvas = null; //todo anything that can be done for it to go to garbage collector
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
//        App.currentGame.resumeScaling();



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
        System.out.println(ScreenManager.getPane("game.fxml").getWidth());
        //todo why does the pane get a width when the code runs the animation timer

    }







    void update(float delta) {
        level.update(delta);


    }



    public void render() {



        level.render(context);
//        for (Balloon balloon : level.getBalloons()) {
//            balloon.render(context);
//        }
//        for (Spike spike : level.getSpikes()) {
//            spike.render(context);
//        }
//
//        for (BaseTurret t: level.getTurrets()) {
//            t.render(context);
//        }
//
//        for(Projectile projectile: level.getProjectiles()){
//            projectile.render(context);
//        }
//
//        for (Projectile projectile: level.getHitProjectiles()){
//
//                if (projectile != null) {
//                    projectile.render(context);
//                }
//
//
//
//        }
//
//
//        context.setFill(Color.BLACK);
//        context.setFont(new Font(45));
//        context.fillText("$" + level.getMoney(), 30, 34);

    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

//    public void takeLives(int lives) {
//        level.setLives(level.getLives() - lives);
//        ;
//    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


}
