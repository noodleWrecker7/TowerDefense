package adtosh.towerdefense.levels;

import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.entity.projectiles.MagicBall;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
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

//    private ArrayList<Class<Projectile>> projectileClasses = new ArrayList<>();
//    private HashMap<String, Class<?>> projectileClasses = new HashMap<String, Class<?>>();
    private HashMap<String, Constructor<? extends Projectile>> projectileConstructors = new HashMap<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();



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
        for (int i = 1; i < mapPathPoints.length; i++) {
            Line line = new Line(mapPathPoints[i - 1][0] / 2, mapPathPoints[i - 1][1] / 2, mapPathPoints[i][0] / 2, mapPathPoints[i][1] / 2);
            line.setStrokeWidth(4);
            path.add(line);

        }

    }

    public void addProjectilesType()  {

//        try {
//            projectileClasses.put("Magic", Class.forName("class adtosh.towerdefense.entity.projectiles.MagicBall"))
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//


        try {

            Constructor<MagicBall> magicBallConstructor = MagicBall.class.getConstructor(double.class, double.class, String.class, Balloon.class);
            projectileConstructors.put("magic ball", magicBallConstructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
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
                bIter.remove();
//                b.remove(bIter);
            }
        }

        for (BaseTurret turret: turrets){
            turret.update(delta);
        }

        for (Projectile projectile: projectiles){
            projectile.update(delta);
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

        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()){
            Projectile projectile = projectileIterator.next();
            if (b.getBounds().intersects(projectile.getBounds().getLayoutBounds())){
                projectile.handleCollision();

                    projectileIterator.remove();


                b.handleCollision(projectile);

            }

        }
    }
    private <T extends Entity & Collidable> void damage(T damager, Iterator<?> iterator, Balloon b){
        if (b.getBounds().intersects(damager.getBounds().getLayoutBounds())){
            damager.handleCollision();
//            if (damager.getLives() <= 0){
                iterator.remove();

//            }
            if (damager instanceof Projectile){
                b.handleCollision((Projectile) damager);
            }
            b.handleSpikeCollision();

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

    public ArrayList<BaseTurret> getTurrets() {
        return turrets;
    }
    public void addToProjectiles (Projectile projectile){
        projectiles.add(projectile);
    }
    public void removeFromProjectiles (Projectile projectile){
        projectiles.remove(projectile);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void removeFromBalloons(Balloon balloon) {

        //todo each balloon has its own duplivate
        this.balloons.remove(balloon);

    }
//    public void addToTurrets(BaseTurret t){
//        turrets.add(t);
//    }

    public boolean isCarryingItem() {
        return carryingItem;
    }

    public void setCarryingItem(boolean carryingItem) {
        this.carryingItem = carryingItem;
    }

    public HashMap<String, Constructor<? extends Projectile>> getProjectileConstructors() {
        return projectileConstructors;
    }

}
