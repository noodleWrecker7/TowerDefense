package adtosh.towerdefense.turrets;

import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Spike extends BaseTurret {

    public Spike(double x, double y) {
        super(x, y);
    }

    public Spike() {
        super();
    }

    @Override
    public void render(GraphicsContext g) {
        System.out.println(x);
        if (x<800*2 - TextureManager.getTexture("spike").getWidth()) {
            g.drawImage(TextureManager.getTexture("spike"), x, y);
        }

    }

    @Override
    public void update(float delta) {

    }
}
;