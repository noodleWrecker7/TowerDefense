package adtosh.towerdefense.levels;

import adtosh.towerdefense.App;
import adtosh.towerdefense.Game;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.projectiles.Dart;
import adtosh.towerdefense.entity.projectiles.MagicBall;
import adtosh.towerdefense.entity.projectiles.Missile;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.turrets.BaseTurret;
import adtosh.towerdefense.turrets.Spike;
import adtosh.towerdefense.turrets.Upgrade;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.lang.reflect.Constructor;
import java.util.*;

// level class, stores data about map, current wave, lives etc
public class Level {

    // these are all fields that should be loaded from file
    private int wave;
    private int lives = 100;
    private boolean carryingItem = false;

    private ArrayList<Balloon> balloons = new ArrayList<>();
    private ArrayList<UnSpawnedBalloon> unSpawnedBalloons = new ArrayList<>();


    private ArrayList<Spike> spikes = new ArrayList<>();
    private ArrayList<BaseTurret> turrets = new ArrayList<>();
    private BaseTurret selectedTurret = null;

    private HashMap<String, Constructor<? extends Projectile>> projectileConstructors = new HashMap<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Projectile> hitProjectiles = new ArrayList<>();

    private int money = 10000;

    private int[][] mapPathPoints;
    private ArrayList<Line> path = new ArrayList<>();

    private int levelID;





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
        String fileName = "map-" + levelID;


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
            line.setStroke(Color.GREEN);
            line.setStrokeWidth(45);
            path.add(line);
//            ScreenManager.addRoot("game.fxml", line);

        }

    }

    public void addProjectilesType() {
        try {
            Constructor<MagicBall> magicBallConstructor = MagicBall.class.getConstructor(double.class, double.class, double.class, int.class, int.class, boolean.class, int.class,  String.class, Balloon.class);
            projectileConstructors.put("magic ball", magicBallConstructor);

            Constructor<Dart> dartConstructor = Dart.class.getConstructor(double.class, double.class, double.class, int.class, int.class, boolean.class, String.class, Balloon.class);
            projectileConstructors.put("dart", dartConstructor);

            projectileConstructors.put("big dart", Dart.class.getConstructor(double.class, double.class, double.class, int.class, int.class, boolean.class, String.class, Balloon.class));

            projectileConstructors.put("missile", Missile.class.getConstructor(double.class, double.class, double.class, int.class, int.class, boolean.class, int.class, String.class, Balloon.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public void drawPath(GraphicsContext g) {

        g.setStroke(Color.DARKGREEN);
        g.setLineWidth(115);
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

        for (Balloon balloon : balloons) {
            balloon.render(g);
        }
        for (Spike spike : spikes) {
            spike.render(g);
        }

        for (BaseTurret t: turrets) {
            t.render(g);
        }

        for(Projectile projectile: projectiles){
            projectile.render(g);
        }

        for (Projectile projectile: hitProjectiles){

            if (projectile != null) {
                projectile.render(g);
            }



        }


        g.setFill(Color.BLACK);
        g.setFont(new Font(45));
        g.fillText("$" + money, 30, 34);
        g.fillText("lives: "+ lives, 200, 34 );


    }

    public void setBalloonsSpawnQueue(ArrayList<String[]> balloonsSpawnQueue) {

        this.timeSinceSpawn = 0;
        for (String[] line: balloonsSpawnQueue) {

            int balloonCount = Integer.parseInt(line[1]);
            int layers = Integer.parseInt(line[0]);
            double delay = Integer.parseInt(line[3]) ;
            double timeBetweenSpawn = Double.parseDouble(line[2]);


            for (int i = 0; i <balloonCount ; i++) {
                double spawnTimeMark = delay + i * timeBetweenSpawn;
                unSpawnedBalloons.add(new UnSpawnedBalloon(layers, spawnTimeMark));
            }

        }

    }






    public void update(double delta) {

//        Iterator<Balloon> bIter = balloons.iterator();
//        while (bIter.hasNext()) {
//            Balloon b = null;
//            try {
//                b = bIter.next();
//
//
//            }catch (ConcurrentModificationException e){
//                System.out.println("here");
//                e.printStackTrace();
//            }
//            b.update(delta);
//            checkSpikeBalloonCollide(b);
////            if (b.getLayers() <= 0) {
////                bIter.remove();
////
////            }
//            if (b.getLayers() < 0) {
//                bIter.remove();
//            }
//        }


        checkSpikeBalloonCollision();
        checkTurretBalloonCollision();
//        checkBalloonRemove();
        balloons.removeIf(balloon -> balloon.getLayers() < 0);

        checkWaveOnGoing();
        checkProjectileInRange();
        manageUpgradeButtons();




        if (App.currentGame.getCurrentState() == Game.GameState.FAST_SPEED) delta *=3;

        checkBalloonSpawn(delta);

        if (App.currentGame.getCurrentState() != Game.GameState.PAUSED) {

            for (Balloon balloon : balloons) {
                balloon.update(delta);
            }


            for (BaseTurret turret : turrets) {

                turret.update(delta);
            }


            for (Projectile projectile : projectiles) {
                projectile.update(delta);
            }
        }

    }

    public void checkLives(){
        if (lives<= 0){
            App.currentGame.returnToMenu();
        }
    }

    private void checkSpikeBalloonCollision(){

        Iterator<Balloon> bIter = balloons.iterator();
        while (bIter.hasNext()) {
//            Balloon b = null;
            try {
                Balloon b = bIter.next();
                checkSpikeBalloonCollide(b);


            }catch (Exception e){
                System.out.println("error");
                e.printStackTrace();
            }
//            b.update(delta);
//            checkSpikeBalloonCollide(b);
//            if (b.getLayers() <= 0) {
//                bIter.remove();
//
//            }
//            if (b.getLayers() < 0) {
//                bIter.remove();
//            }
        }

    }

    private void checkBalloonRemove(){
        Iterator<Balloon> iterator = balloons.iterator();
        while (iterator.hasNext()){
            Balloon b = iterator.next();
            if (b.getLayers() < 0){

            }
        }
    }


    private double timeSinceSpawn;


    private void checkBalloonSpawn(double delta){
        timeSinceSpawn+= delta;



            Iterator<UnSpawnedBalloon> queueIter = unSpawnedBalloons.iterator();
            while (queueIter.hasNext()){

                UnSpawnedBalloon unSpawnedBalloon = queueIter.next();
                if (timeSinceSpawn >=unSpawnedBalloon.getSpawnTimeMark()) {

                    unSpawnedBalloon.createBalloon();
                    queueIter.remove();
                }

            }

    }



    private void manageUpgradeButtons() {
        //todo TEST
        Button button1 =((Button) ScreenManager.getNode("1"));
        Button button2 =((Button) ScreenManager.getNode("2"));



        if (this.selectedTurret != null) {

            if (this.selectedTurret.getCurrentUpgrade1()!=null) {
                Upgrade upgrade = this.selectedTurret.getCurrentUpgrade1();
                button1.setText(upgrade.getDescription() + "\n $" + upgrade.getCost());
            }else {
                button1.setText("");
            }

            if (this.selectedTurret.getCurrentUpgrade2() != null) {
                Upgrade upgrade = this.selectedTurret.getCurrentUpgrade2();
                button2.setText(upgrade.getDescription() + "\n $" + upgrade.getCost());
            }else {
                button2.setText("");
            }
        }else {
            button1.setText("");
            button2.setText("");
        }
    }

    private void checkWaveOnGoing() {

        if (balloons.size() <= 0 && unSpawnedBalloons.size() <= 0) {
            App.currentGame.setCurrentState(Game.GameState.ROUND_INACTIVE);
//            this.waveOnGoing = false;
        }
    }

    private void checkProjectileInRange() {
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            if (projectile.getSource() != null) {
                //if it doesnt have a source its because theres no need for it to be used as its not gonna be limited in range
                if (!App.currentGame.collides(projectile.getSource().getRangeBounds(), (Rectangle) projectile.getBounds())) {
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
//                    if (projectile.isHit()) {continue;}
//                    projectile.setHit(true);
                    projectile.getSplashedBalloons().clear();

                    String initialTexture = projectile.getTextureName();
                    projectile.handleCollision(b);
                    String afterTexture = projectile.getTextureName();


                    balloonsToPop.put(projectile, projectile.getSplashedBalloons());

                    if (!initialTexture.equals(afterTexture)){
                        hitProjectiles.add(projectile);
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                hitProjectiles.remove(projectile);
                                timer.cancel();
                            }
                        }, 200);
                    }
                    if (projectile.getLives() <0){
                        projectileIterator.remove();
                    }


//                    if (projectile.getLives() < 0) {
//
//                        projectileIterator.remove();
//
//                        if (!initialTexture.equals(afterTexture)) {
//                            hitProjectiles.add(projectile);
//
//                            Timer timer = new Timer();
//
//                            timer.schedule(new TimerTask() {
//                                @Override
//                                public void run() {
//
//                                    //instance variables are accessed by object reference so we can access them here without making final
//                                    //local variables are accessed because java makes a copy of them, and to stop the copy or original value changing it must be final
//
//                                    hitProjectiles.remove(projectile);
//                                    timer.cancel();
//                                }
//
//                            }, 200);
//
//
//                        }
//                    }

                } else if (projectile.getX() > canvas.getWidth() * 2 + 50 || projectile.getX() < -50 || projectile.getY() > canvas.getHeight() * 2 + 50 || projectile.getY() < -50) {
                    projectileIterator.remove();

                }

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

        //if a balloon has been removed and its still checking  for other spikes then null pointed

        Iterator<Spike> spikeIterator = getSpikes().iterator();
        while (spikeIterator.hasNext()) {
            Spike spike = spikeIterator.next();

//            if (b == null)return;

            if (b.getBounds().intersects(spike.getBounds().getLayoutBounds()) && spike.isPlaced()) {
                spike.handleBalloonCollision();
                if (spike.getLives() <= 0) {
                    spikeIterator.remove();
                }
                b.handleSpikeCollision();
                if (b.getLayers()<0)return;



            }

        }

    }

    public void selectTurret(MouseEvent e) {

        for (BaseTurret turret : turrets) {
            if (checkTurretPressed(e, turret)) {
                unSelectAllTurrets();
                turret.select();
                this.selectedTurret = turret;
                break;


            } else {
                if (turret.isSelected()) {
                    turret.unSelect();
                    this.selectedTurret = null;
                }
            }

        }
    }

    public void unSelectAllTurrets() {
        for (BaseTurret turret : turrets) {
            turret.unSelect();
        }
        this.selectedTurret = null;
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


    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
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

    public HashMap<String, Constructor<? extends Projectile>> getProjectileConstructors() {
        return projectileConstructors;
    }

    public ArrayList<Projectile> getHitProjectiles() {
        return hitProjectiles;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLives() {
        return lives;
    }

    public void takeLives(int lives) {
        this.lives -= lives;
    }





    public BaseTurret getSelectedTurret() {
        return selectedTurret;
    }
}
