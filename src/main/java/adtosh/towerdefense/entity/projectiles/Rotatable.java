package adtosh.towerdefense.entity.projectiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public interface Rotatable {

    default void rotate(GraphicsContext g, double angle, double px, double py){
        Rotate r = new Rotate(angle, px, py);
        g.setTransform(r.getMxx() / 2, r.getMyx() / 2, r.getMxy() / 2, r.getMyy() / 2, r.getTx() / 2, r.getTy() / 2);
    }
}
