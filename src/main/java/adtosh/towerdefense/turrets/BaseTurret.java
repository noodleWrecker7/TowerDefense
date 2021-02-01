package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public abstract class BaseTurret extends Entity {

    private boolean isPlaced = false;

    public BaseTurret(double x, double y, String texture) {
        super(x, y, texture);
        App.currentGame.getLevel().addToTurrets(this);
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
                App.currentGame.getLevel().setCarryingItem(false);
                break;
            }
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
