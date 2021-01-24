package adtosh.towerdefense.levels;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;

// level class, stores data about map, current wave, lives etc
public class Level {

    private int wave;
    private int lives;
    //    private Balloon[] balloons;
    private ArrayList<Balloon> balloons = new ArrayList<>();
    //arraylist would be easier in this situation?

    private int[][] mapPath;
    private Path path;

    private int levelID;

    private int startX;
    private int startY;

    public Level(int levelID) {
        this.levelID = levelID;

    }

    public void loadDataFromFile() {
//        balloons.add(new Balloon(startX, startY, 50, 50, Assets.balloon ));
        //todo create a timer so that each ballon is released seperately
        //for now we should just work with one tho

    }

    public void addBalloons() {
        balloons.add(new Balloon(startX, startY, 0));
    }


    public void loadPath(int level) {

        mapPath = LevelPaths.paths[level];
        path = new Path();
        MoveTo start = new MoveTo(mapPath[0][0], mapPath[0][1]);
        path.getElements().add(start);

        for (int i = 1; i < mapPath.length; i++) {
            LineTo line = new LineTo(mapPath[i][0], mapPath[i][1]);
            path.getElements().add(line);
        }
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
        balloons.get(0).render(g);
        this.drawPath(g);

    }

    public int getLevelID() {
        return levelID;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
