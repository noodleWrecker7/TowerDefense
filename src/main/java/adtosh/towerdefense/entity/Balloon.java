package adtosh.towerdefense.entity;

import adtosh.towerdefense.Assets;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;

public class Balloon extends Entity {

    public void handleCollision(Projectile p) { // todo

    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(Assets.balloon, x, y, width, height);
    }

    @Override
    public void update(float delta) {

    }
}
