package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class BaseTurret extends Entity {

    private boolean isPlaced = false;

    double range;

    public BaseTurret(double x, double y, String texture) {
        super(x, y, texture);
    }

    public void setMouseMoveListener() {
        Canvas canvas = App.currentGame.getCanvas();


        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent e){
        if(isPlaced) return;
        for (Line line : App.currentGame.getLevel().getPath()) {
            if (this.getBounds().intersects(line.getLayoutBounds())) {
                App.currentGame.getCanvas().setOnMouseMoved(event -> { });
                isPlaced = true;
                break;
            }
        }
    }

    public double getRange(){
        return range;
    }

    @Override
    public void render(GraphicsContext g){
        super.render(g);
        if (!isPlaced) {
            g.setFill(new Color(0.2,0.2,0.2, 0.2));
            g.fillOval(x -getRange(), y-getRange(), getRange()*2, getRange()*2);
        }
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
