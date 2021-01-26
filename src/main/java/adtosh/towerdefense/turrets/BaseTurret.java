package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class BaseTurret extends Entity {

    boolean isPlaced;

    public BaseTurret(double x, double y, String texture) {
        super(x, y, texture);
    }

    public void setMouseMoveListener() {
        Canvas canvas = App.currentGame.getCanvas();

        canvas.setOnMouseMoved(e -> {
            y = (e.getY() * 2);
            x = (e.getX() * 2);
            this.hitBox.setX(e.getX());
            this.hitBox.setY(e.getY());
            this.hitBox.setFill(Color.RED);

        });

        canvas.setOnMouseClicked(e -> {
            //check collision of path and hitbox
            for (Line line : App.currentGame.getLevel().getPath()) {


                if (this.hitBox.intersects(line.getLayoutBounds())) {
                    //todo find a way to work round scaled coordinates or do something better
                    System.out.println("HERE");
                    canvas.setOnMouseMoved(event -> {
                    });
                    //do nothing
                    break;

                }
            }
        });
    }


}
