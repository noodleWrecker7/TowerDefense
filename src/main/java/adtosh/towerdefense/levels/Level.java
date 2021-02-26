package adtosh.towerdefense.levels;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Collidable;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.entity.projectiles.Dart;
import adtosh.towerdefense.entity.projectiles.MagicBall;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Constructor;
import java.util.*;

// level class, stores data about map, current wave, lives etc
public class Level {

    // these are all fields that should be loaded from file
    private int wave;
    private int lives;
    private boolean carryingItem = false;

    private ArrayList<Balloon> balloons = new ArrayList<>();
    private ArrayList<Spike> spikes = new ArrayList<>();
    private ArrayList<BaseTurret> turrets = new ArrayList<>();

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

    public void addProjectilesType() {
        try {
            Constructor<MagicBall> magicBallConstructor = MagicBall.class.getConstructor(double.class, double.class, double.class,int.class, int.class, String.class, Balloon.class);
            projectileConstructors.put("magic ball", magicBallConstructor);
            Constructor<Dart> dartConstructor =Dart.class.getConstructor(double.class, double.class, double.class, int.class, int.class, String.class, Balloon.class);
            projectileConstructors.put("dart", dartConstructor );
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

        if (timeSinceSpawn > TimeTilSpawn ) {
            Random rand = new Random();

            new Balloon(rand.nextInt(7), "balloon-0");
            timeSinceSpawn = 0;
        }

        Iterator<Balloon> bIter = balloons.iterator();
        while (bIter.hasNext()) {
            Balloon b = bIter.next();
            b.update(delta);
            checkSpikeBalloonCollide(b);
            if (b.getLayers() <= 0) {
                bIter.remove();

            }
        }

        checkTurretBalloonCollision();

        balloons.removeIf(balloon -> balloon.getLayers() <= 0);

        checkProjectileInRange();


        for (BaseTurret turret : turrets) {

            turret.update(delta);
        }




        for (Projectile projectile : projectiles) {
            projectile.update(delta);
        }
    }

    private void checkProjectileInRange(){
        Iterator <Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()){
            Projectile projectile = projectileIterator.next();
            if (projectile.getSource()!= null){
                //if it doesnt have a source its because theres no need for it to be used as its not gonna be limited in range
                if (!App.currentGame.collides(projectile.getSource().getRangeBounds(), (Rectangle) projectile.getBounds())){
                    projectileIterator.remove();

                }


            }
        }


    }

    private void checkTurretBalloonCollision() {
        HashMap<Projectile, ArrayList<Balloon>> balloonsToPop = new HashMap<>();

        for (Balloon b : balloons) {

            Iterator<Projectile> projectileIterator = projectiles.iterator();
            while (projectileIterator.hasNext()) {
                Projectile projectile = projectileIterator.next();
                Canvas canvas = App.currentGame.getCanvas();

                if (projectile.getBounds().intersects(b.getBounds().getLayoutBounds())) {
                    projectile.getSplashedBalloons().clear();
                    projectile.handleCollision(b);
                    balloonsToPop.put(projectile, projectile.getSplashedBalloons());
                    projectileIterator.remove();
                }
                else if (projectile.getX() > canvas.getWidth() * 2 + 50 || projectile.getX() < -50 || projectile.getY() > canvas.getHeight() * 2 + 50 || projectile.getY() < -50) {
                    projectileIterator.remove();

                }
                //todo warning illegal state exception
            }

        }

        for (Map.Entry<Projectile, ArrayList<Balloon>> pair : balloonsToPop.entrySet()) {

            Projectile projectile = pair.getKey();
            ArrayList<Balloon> balloonsToDamage = pair.getValue();


            for (Balloon balloon : balloonsToDamage) {
                balloon.handleCollision(projectile);

            }

        }
    }

    public void checkSpikeBalloonCollide(Balloon b) {
        Iterator<Spike> spikeIterator = getSpikes().iterator();
        while (spikeIterator.hasNext()) {
            Spike spike = spikeIterator.next();
            if (b.getBounds().intersects(spike.getBounds().getLayoutBounds()) && spike.isPlaced()) {
                spike.handleBalloonCollision();
                if (spike.getLives() <= 0) {
                    spikeIterator.remove();
                }
                b.handleSpikeCollision();

            }

        }

    }

    public void selectTurret(MouseEvent e) {
        for (BaseTurret turret : turrets) {
            if (checkTurretPressed(e, turret)) {
                turret.select();
//                App.currentGame.getCanvas().setOnMouseClicked(this::unSelectTurret);
            } else {
                if (turret.isSelected()) {
                    turret.unSelect();
                }
            }

        }
    }

    public void unSelectAllTurrets(){
        for (BaseTurret turret: turrets){
            turret.unSelect();
        }
    }


    private boolean checkTurretPressed(MouseEvent e, BaseTurret turret) {
        if (e.getSceneX() * 2 < turret.getX() + TextureManager.getTexture(turret.getTextureName()).getWidth() / 2) {
            if (e.getSceneX() * 2 > turret.getX() - TextureManager.getTexture(turret.getTextureName()).getWidth() / 2) {

                if (e.getSceneY() * 2 < turret.getY() + TextureManager.getTexture(turret.getTextureName()).getHeight() / 2) {
                    if (e.getSceneY() * 2 > turret.getY() - TextureManager.getTexture(turret.getTextureName()).getHeight() / 2) {

                        return true;

                    }
                }

            }
        }
        return false;
    }


    public int getLevelID() {
        return levelID;
    }

    public ArrayList<Line>  getPath() {
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

    public void addToTurrets(BaseTurret baseTurret) {
        this.turrets.add(baseTurret);
    }

    public void removeTurret(BaseTurret baseTurret) {
        this.turrets.remove(baseTurret);
    }

    public ArrayList<BaseTurret> getTurrets() {
        return turrets;
    }

    public void addToProjectiles(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void removeFromProjectiles(Projectile projectile) {
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
