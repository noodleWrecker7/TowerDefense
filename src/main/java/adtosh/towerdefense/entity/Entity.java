package adtosh.towerdefense.entity;

import adtosh.towerdefense.TextureManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.util.Iterator;

public abstract class Entity {
    protected double x;
    protected double y;

    protected double width;
    protected double height;

    protected String textureName;



    public Entity(double x, double y, String texture) {
        this(texture);
        this.x = x;
        this.y = y;

    }

    public Entity(String _textureName) {
        Image texture = TextureManager.getTexture(_textureName);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.textureName = _textureName;
    }

//    public void remove(){
//        App.currentGame.removeFromEntity(this);
//    }

    public void remove(Iterator<?> iterator){
//        App.currentGame.removeFromEntity(this);
        iterator.remove();
//        iterator.next();

    }


    public abstract Shape getBounds();

    public void render(GraphicsContext g){

        g.drawImage(TextureManager.getTexture(textureName), x - width / 2, y - height / 2);
    }

    public abstract void update(double delta);

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getTextureName() {
        return textureName;
    }
}
