package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.Balloon;
import adtosh.towerdefense.entity.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public abstract class BaseTurret extends Entity {

    private boolean isPlaced = false;

    protected double range;
    private Balloon target;
    private double angle = 0;

    private double timeSinceSpawn = 0;
    private final double TimeTilSpawn = 1.0d;

    public BaseTurret(double x, double y, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToTurrets(this);
    }

    public void setMouseMoveListener() {
        Canvas canvas = App.currentGame.getCanvas();
        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseClicked(this::handleMouseClick);
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
//        ScreenManager.addRoot("game.fxml", this.getRangeBounds());
    }

    public double getRange() {
        return range;
    }

    @Override
    public void render(GraphicsContext g) {
        g.save();
        rotate(g, angle, x, y);
        super.render(g);
        g.restore();

        if (!isPlaced) {
            g.setFill(new Color(0.2, 0.2, 0.2, 0.2));
            g.fillOval(x - range, y - range, range * 2, range * 2);
        }

    }

    @Override
    public void update(float delta) {

        if (this.target == null && isPlaced) {
            findTarget();
        }
        if (this.target != null && isPlaced) {
            if (!target.getBounds().intersects(this.getRangeBounds().getLayoutBounds())){
                target = null;
                return;
            }

            findAngle();
            timeSinceSpawn += delta;
            if (timeSinceSpawn > TimeTilSpawn) {
                timeSinceSpawn = 0;

            }

        }


    }

    private void findAngle() {
        this.angle = Math.toDegrees(Math.atan2(x - target.getX(), y- target.getY())) * -1;
        if (angle < 0) {
            angle += 360;
        }




        System.out.println(angle);
    }

    private void findTarget() {
        //todo make it prefer the balloon that is in the lead
        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (balloon.getBounds().intersects(this.getRangeBounds().getLayoutBounds())) {
                this.target = balloon;
                break;
            }
        }
    }

    public Circle getRangeBounds() {
        return new Circle(x / 2, y / 2, range / 2);
    }

    private void rotate(GraphicsContext g, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        g.setTransform(r.getMxx() / 2, r.getMyx() / 2, r.getMxy() / 2, r.getMyy() / 2, r.getTx() / 2, r.getTy() / 2);

    }

    private void handleMouseMove(MouseEvent e) {
        if (isPlaced) return;
        y = (e.getY() * 2);
        x = (e.getX() * 2);
    }

    public boolean isPlaced() {
        return isPlaced;
    }
}
