package adtosh.towerdefense.entity;

import adtosh.towerdefense.Assets;
import adtosh.towerdefense.entity.projectiles.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Balloon extends Entity {
    private Image currentTexture;
    private ArrayList<Image> textureList = new ArrayList<>();

    public Balloon(double x, double y, double width, double height, Image currentTexture) {
        super(x, y, width, height);
        this.currentTexture = currentTexture;

    }

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
