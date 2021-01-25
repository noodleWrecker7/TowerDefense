package adtosh.towerdefense.levels;

import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// level class, stores data about map, current wave, lives etc
public class Level {

    // these are all fields that should be loaded from file
    private int wave;
    private int lives;
    //    private Balloon[] balloons;
    private ArrayList<Balloon> balloons = new ArrayList<>();
    public static ArrayList<Entity> entities = new ArrayList<>();
    //todo possibly pass a level object to every class so entities doesnt have to be static

    private int[][] mapPath;
    private ArrayList<Line> path = new ArrayList<>();

    private int levelID;


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }



    private int startX;
    private int startY;

    public Level(int levelID) {
        this.levelID = levelID;
        loadDataFromFile();

    }

    public void removeBalloon(Balloon b) {
        balloons.remove(b);
    }

    public int[] getPathPoint(int index) {
        return mapPath[index];
    }

    public int pathLength() {
        return mapPath.length;
    }

    public void loadDataFromFile() {
        //todo acc make a file


        startX = 0;
        startY = 540;

    }




    public void loadPath(int level) {
        mapPath = LevelPaths.paths[level];
        startX = mapPath[0][0];
        startY = mapPath[0][1];
    }

    public void initialisePath(){
        //todo ADAM THIS IS FOR CHECKING IF THE SPIKES INTERSECT WITH THE GROUND, so I can check if they are supposed to be placed
        //todo make this check intersection
        for (int i = 1; i <mapPath.length ; i++) {
            Line line = new Line(mapPath[i-1][0]/2, mapPath[i-1][1]/2, mapPath[i][0]/2, mapPath[i][1]/2);
            line.setStrokeWidth(4);
//            line.setStroke(Color.TRANSPARENT);
            path.add(line);
//            ScreenManager.addRoot(ScreenManager.getCurrentPage(), line);
        }

    }

    // debug method
    public void drawPath(GraphicsContext g) {
//         below is temporary for debugging
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

        g.drawImage(TextureManager.getTexture("grass"), 0, 0);
        for (Entity entity: entities){
            entity.render(g);
        }

        this.drawPath(g);
    }

    float timeSinceSpawn = 0;
    final float TimeTilSpawn = 0.5f;

    public void update(float delta) {
        timeSinceSpawn += delta;
        if (timeSinceSpawn > TimeTilSpawn) {
            Random rand = new Random();
            balloons.add(new Balloon( rand.nextInt(7), TextureManager.getTexture("balloon-0")));
            timeSinceSpawn = 0;
        }

        Iterator <Balloon>bIter = balloons.iterator();
        while(bIter.hasNext()) {
            Balloon b =  bIter.next();
            b.update(delta);
            if (b.getLayers() < 0) {
                bIter.remove();
            }
        }
    }

    public int getLevelID() {
        return levelID;
    }

    public ArrayList<Line> getPath() {
        return path;
    }
}
