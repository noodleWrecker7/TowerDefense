package adtosh.towerdefense;

import adtosh.towerdefense.entity.CoOrdinates;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public interface Placeable  {

    default void setMouseMoveListener(){
        Canvas canvas = App.currentGame.getCanvas();
        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseClicked(this::handleMouseClick);

    }
    void handleMouseMove(MouseEvent event);

    void handleMouseClick(MouseEvent event);


}
