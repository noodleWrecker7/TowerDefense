package adtosh.towerdefense.levels;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// level class, stores data about map, current wave, lives etc
public class Level {

    // these are all fields that should be loaded from file
    private int wave;
    private int lives;
    //    private Balloon[] balloons;
    private ArrayList<Balloon> balloons = new ArrayList<>();
    //arraylist would be easier in this situation?

    private int[][] mapPath;

    private int levelID;

    private int startX;
    private int startY;

    public Level(int levelID) {
        this.levelID = levelID;
        loadDataFromFile();

    }

    public int[] getPathPoint(int index) {
        return mapPath[index];
    }

    public void loadDataFromFile() {
//        balloons.add(new Balloon(startX, startY, 50, 50, Assets.balloon ));
        //todo create a timer so that each ballon is released seperately
        //for now we should just work with one tho

        startX = 0;
        startY = 540;

    }

    public void addBalloons() {
        balloons.add(new Balloon(startX, startY, 0));
    }


    public void loadPath(int level) {
        mapPath = LevelPaths.paths[level];
    }

    // debug method
    public void drawPath(GraphicsContext g) {
        // below is temporary for debugging
        g.setStroke(Color.GREEN);
        g.setLineWidth(4);
        g.beginPath();

        g.moveTo(mapPath[0][0], mapPath[0][1]);

        for (int i = 1; i < mapPath.length; i++) {
            g.lineTo(mapPath[i][0], mapPath[i][1]);
        }
        g.stroke();

    }

    public void render(GraphicsContext g) {
//        context.drawImage(TextureManager.grass, 0, 0, 1550, 1150);
//        context.drawImage(TextureManager.balloon, 50, 50, 140, 40);
        g.drawImage(TextureManager.getTexture("grass"), 0, 0);

        for (Balloon b : balloons) {
            b.render(g);
        }
        this.drawPath(g);
    }

    public void update(float delta) {
        for (Balloon b : balloons) {
            b.update(delta);
        }
    }

    public int getLevelID() {
        return levelID;
    }

}
