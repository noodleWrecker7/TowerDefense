package adtosh.towerdefense.levels;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
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
    private boolean carryingItem = false;

    private ArrayList<Balloon> balloons = new ArrayList<>();
    private ArrayList<Spike> spikes = new ArrayList<>();
    private ArrayList<BaseTurret> turrets = new ArrayList<>();
    //to

    //todo possibly pass a level object to every class so entities doesnt have to be static

    private int[][] mapPathPoints;
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
        return mapPathPoints[index];
    }

    public int pathLength() {
        return mapPathPoints.length;
    }

    public void loadDataFromFile() {
        //todo acc make a file


        startX = 0;
        startY = 540;

    }

    public void loadPath(int level) {
        mapPathPoints = LevelPaths.paths[level];
        startX = mapPathPoints[0][0];
        startY = mapPathPoints[0][1];
    }

    public void initialisePath() {
        //todo ADAM THIS IS FOR CHECKING IF THE SPIKES INTERSECT WITH THE GROUND, so I can check if they are supposed to be placed
        //todo make this check intersection
        for (int i = 1; i < mapPathPoints.length; i++) {
            Line line = new Line(mapPathPoints[i - 1][0] / 2, mapPathPoints[i - 1][1] / 2, mapPathPoints[i][0] / 2, mapPathPoints[i][1] / 2);
            line.setStrokeWidth(4);
            path.add(line);

        }

    }


    public void drawPath(GraphicsContext g) {
//         below is temporary for debugging
        g.setStroke(Color.GREEN);
        g.setLineWidth(4);
        g.beginPath();

        g.moveTo(mapPathPoints[0][0], mapPathPoints[0][1]);

        for (int i = 1; i < mapPathPoints.length; i++) {
            g.lineTo(mapPathPoints[i][0], mapPathPoints[i][1]);
        }
        g.stroke();

    }

    public void render(GraphicsContext g) {

        g.drawImage(TextureManager.getTexture("grass"), 0, 0);
        this.drawPath(g);
    }

    float timeSinceSpawn = 0;
    final float TimeTilSpawn = 0.5f;


    public void update(float delta) {

        timeSinceSpawn += delta;

        if (timeSinceSpawn > TimeTilSpawn) {
            Random rand = new Random();
            //todo possibly move add balloon to contructor
//            balloons.add(new Balloon(rand.nextInt(7), "balloon-0"));
            new Balloon(rand.nextInt(7), "balloon-0");
            timeSinceSpawn = 0;
        }

        Iterator<Balloon> bIter = balloons.iterator();
        while (bIter.hasNext()) {
            Balloon b = bIter.next();
            b.update(delta);
            checkBalloonCollide(b);
            if (b.getLayers()<0){
                b.remove(bIter);
            }
        }
    }

    public void checkBalloonCollide(Balloon b){
        Iterator<Spike> spikeIterator = getSpikes().iterator();
        while (spikeIterator.hasNext()) {
            Spike spike = spikeIterator.next();

            if (b.getBounds().intersects(spike.getBounds().getLayoutBounds()) && spike.isPlaced()) {

                spike.handleBalloonCollision();
                if (spike.getLives() <= 0) {
                    spikeIterator.remove();
                }
                b.handleSpikeCollision();
                    /*if (balloon.getLayers() <= 0) {
                        balloonIterator.remove();
                    }*/
            }

        }
    }

    public int getLevelID() {
        return levelID;
    }

    public ArrayList<Line> getPath() {
        return path;
    }

    public ArrayList<Balloon> getBalloons() {
        return balloons;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

//    public <T> void addToList(T add){
//
//    }


    public void addToSpikes(Spike spike) {
        this.spikes.add(spike);

    }

    public void removeFromSpikes(Spike spike) {
        this.spikes.remove(spike);

    }

    public void addToBalloons(Balloon balloon) {
        this.balloons.add(balloon);

    }

    public void addToTurrets(BaseTurret baseTurret){
        this.turrets.add(baseTurret);
    }

    public void removeTurret(BaseTurret baseTurret){
        this.turrets.remove(baseTurret);
    }


    public void removeFromBalloons(Balloon balloon) {

        //todo each balloon has its own duplivate
        this.balloons.remove(balloon);

    }

    public boolean isCarryingItem() {
        return carryingItem;
    }

    public void setCarryingItem(boolean carryingItem) {
        this.carryingItem = carryingItem;
    }
}
