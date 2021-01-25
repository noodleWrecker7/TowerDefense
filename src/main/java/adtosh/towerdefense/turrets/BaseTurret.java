package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public abstract class BaseTurret extends Entity {


    public BaseTurret(double x, double y, Image texture) {
        super(x, y, texture);
    }

    public BaseTurret() {
        super();
    }

    public  void setMouseMoved(){
        Canvas canvas = App.currentGame.getCanvas();

        canvas.setOnMouseMoved(e->{
            y= (e.getY() *2);
            x=(e.getX()*2);
            this.hitBox.setX(this.x/2);
            this.hitBox.setY(this.y/2);
            //
        });

        canvas.setOnMouseClicked(e ->{
            //check collision of path and hitbox
            for (Line line: App.currentGame.getLevel().getPath()){


                if (this.hitBox.intersects(line.getLayoutBounds())){
                    //todo find a way to work round scaled coordinates or do something better
                    System.out.println("HERE");
                    canvas.setOnMouseMoved(event -> {});
                    //do nothing
                    break;

                }
            }

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
