package adtosh.towerdefense.entity.projectiles;

import adtosh.towerdefense.entity.Balloon;
import javafx.scene.canvas.GraphicsContext;


// parent for all projectiles
public interface Projectile {

    int power = 0; // layers left that it can pop
    float x = 0, y = 0;
    boolean canPopLeads = false; // probably make more of these


    public void handleCollision(Balloon b); // special effects like burn freeze?

    // todo draw it!!!
    public void draw(GraphicsContext g);
}
