package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.Canvas;

public abstract class BaseTurret extends Entity {


    public BaseTurret(double x, double y) {
        super(x, y);
    }

    public BaseTurret() {
        super();
    }

    public  void setMouseMoved(){
        Canvas canvas = App.currentGame.getCanvas();

        canvas.setOnMouseMoved(e->{
            this.setY(e.getY() *2);
            this.setX(e.getX()*2);
        });

        canvas.setOnMouseClicked(e ->{
            //check if touching the path
//            if (this.hitBox.intersects())
            canvas.setOnMouseMoved(event -> {
                //do nothing

            });
        });
    }
//        canvas.setOnMouseMoved(e-> {
//            turret.setY(e.getY()*2);
//            turret.setX(e.getX()*2);
//        });
//        canvas.setOnMouseClicked(event -> {
//            if (touching)
//                canvas.setOnMouseMoved(e->{
//
//                    //so nothing
//                });
//        });

}
