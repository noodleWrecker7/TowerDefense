package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import adtosh.towerdefense.entity.projectiles.Projectile;
import adtosh.towerdefense.entity.projectiles.Rotatable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseTurret extends Entity implements Rotatable {

    protected boolean isPlaced = false;

    protected int range;
//    private Balloon target;
    protected Balloon target;
    protected int power;
    protected double angle =0;
    protected double correctiveAngle=0;

    protected double timeSinceSpawn = 0;
    protected double timeTilSpawn;

    private boolean selected = false;
    protected String projectileName;

    protected int penetration;

    protected boolean canPopLead;



    private ArrayList<Upgrade> upgradeList1 = new ArrayList<>();
    private ArrayList<Upgrade> upgradeList2 = new ArrayList<>();



    protected void addUpgradeList1(Upgrade item){
        upgradeList1.add(item);
    }

    protected void addUpgradeList2(Upgrade item){
        upgradeList2.add(item);
    }


    public  Upgrade getCurrentUpgrade1(){

        if (upgradeList1.size()>upgradeNumber1) {
            return upgradeList1.get(upgradeNumber1);
        }

        return null;


    }
    public  Upgrade getCurrentUpgrade2(){

        if (upgradeList2.size()>upgradeNumber2) {
            return upgradeList2.get(upgradeNumber2);
        }

        return null;


    }



    public void applyUpgrade1(){
        this.upgradeList1.get(upgradeNumber1).applyUpgrade();
        this.upgradeNumber1++;

    }

    public void applyUpgrade2(){
        this.upgradeList2.get(upgradeNumber2).applyUpgrade();
        this.upgradeNumber2++;

    }

    public ArrayList<Upgrade> getUpgradeList1() {
        return upgradeList1;
    }
    public ArrayList<Upgrade> getUpgradeList2() {
        return upgradeList2;
    }

    private int upgradeNumber1 =0;
    private int upgradeNumber2 =0;







    public BaseTurret(double x, double y, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToTurrets(this);

    }

    public void setMouseMoveListener() {

        Canvas canvas = App.currentGame.getCanvas();
        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseClicked(this::handleMouseClick);
        App.currentGame.getLevel().unSelectAllTurrets();
    }

    private void handleMouseClick(MouseEvent e) {

        if (isPlaced) return;
        //this must check every single line and make the decision after
        for (Line line : App.currentGame.getLevel().getPath()) {
            if (this.getBounds().intersects(line.getLayoutBounds())) {
                return;
            }

        }
        App.currentGame.getCanvas().setOnMouseMoved(event -> {
        });
        isPlaced = true;
        App.currentGame.getLevel().setCarryingItem(false);
        App.currentGame.getCanvas().setOnMouseClicked(App.currentGame.getLevel()::selectTurret);
//        ScreenManager.addRoot("game.fxml", this.getRangeBounds());
    }

    protected abstract void initialiseUpgrades();





    @Override
    public Shape getBounds() {

            double radius = TextureManager.getTexture(this.textureName).getWidth() / 2;
            return new Circle(x / 2, y / 2, radius / 2);


    }

    @Override
    public void render(GraphicsContext g) {
        g.save();
        rotate(g, angle + correctiveAngle, x, y);
        super.render(g);
        g.restore();

        if (!isPlaced || selected) {
            g.setFill(new Color(0.2, 0.2, 0.2, 0.2));
            g.fillOval(x - range, y - range, range * 2, range * 2);
        }

    }

    @Override
    public void update(double delta) {

        if(!isPlaced) return;




        if (target != null) {
            if (target.getLayers() <= 0) {
                target= null;
            }
        }





        findFurthestBalloon();

        if (this.target != null ) {

            if (!App.currentGame.collides(this.getRangeBounds(), target.getBounds())){
                target = null;

                return;
            }

            findAngle();
//            if (App.currentGame.getCurrentState() == Game.GameState.FAST_SPEED) {
//                delta *= 2;
//            }

            timeSinceSpawn += delta;

            if (timeSinceSpawn > timeTilSpawn) {
                timeSinceSpawn = 0;
                fire();
            }

        }

    }





    protected void fire(){
        try {
            Constructor<? extends  Projectile> constructor = App.currentGame.getLevel().getProjectileConstructors().get(projectileName);
            constructor.newInstance(x, y, angle, power, penetration, projectileName, target);


        } catch ( InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void findAngle() {
        this.angle = Math.toDegrees(Math.atan2(x - target.getX(), y - target.getY())) * -1;
        if (angle < 0) {
            angle += 360;
        }


    }

    private void findFurthestBalloon() {


        List<Balloon> options = App.currentGame.getLevel().getBalloons().stream()
                .filter(balloon -> App.currentGame.collides(this.getRangeBounds(), balloon.getBounds()))
                .collect(Collectors.toList());

        if (options.size()==0) return;

        double max = options.get(0).getDistanceTravelled();

        for (int i = 0; i <options.size() ; i++) {
            if (options.get(i).getDistanceTravelled() > max) {
                max = options.get(i).getDistanceTravelled();
            }
        }



        for (Balloon balloon : options) {
            if (balloon.getDistanceTravelled() == max) {
                target = balloon;
            }
        }


    }

    private void findTarget() {
        //todo make it prefer the balloon that is in the lead
        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (App.currentGame.collides(this.getRangeBounds(), balloon.getBounds())){
                this.target = balloon;
                break;
            }

        }
    }

    public Circle getRangeBounds() {

         return new Circle(x / 2, y / 2, range / 2);

    }


    private void handleMouseMove(MouseEvent e) {
        if (isPlaced) return;
        y = (e.getY() * 2);
        x = (e.getX() * 2);
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

//    public Balloon getTarget() {
//        return target;
//    }

    public void select(){
        this.selected = true;

    }
    public  void unSelect(){
        this.selected = false;
    }



    public double getRange() {
        return range;
    }

    public int getUpgradeNumber1() {
        return upgradeNumber1;
    }

    public int getUpgradeNumber2() {
        return upgradeNumber2;
    }

    public double getTimeTilSpawn() {
        return timeTilSpawn;
    }

    public void setTimeTilSpawn(double timeTilSpawn) {
        this.timeTilSpawn = timeTilSpawn;
    }
}
