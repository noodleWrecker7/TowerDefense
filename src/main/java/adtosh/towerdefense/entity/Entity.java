package adtosh.towerdefense.entity;

import adtosh.towerdefense.App;
import adtosh.towerdefense.ScreenManager;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.levels.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Entity {
    protected double x;
    protected double y;

    protected double width;
    protected double height;

    protected Rectangle hitBox;

    protected String textureName;

//    public Entity() {
//
//    }

    public Entity(double x, double y, String texture) {
        this(texture);
        this.x = x;
        this.y = y;
//        hitBox = new Rectangle(x, y, width / 2, height / 2);


//        this.width = texture.getWidth();
//        this.height = texture.getHeight();
//        hitBox= new Rectangle(x, y, width, height );
        //constructor chaining so that not every entity needs an x and y passed
    }

    public Entity(String _textureName) {
        App.currentGame.addToEntity(this);
        Image texture = TextureManager.getTexture(_textureName);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        textureName = _textureName;

//        this doesnt actually work because x and y arent defined when its run and it would become irrelevant as soon as it moves too
//        hitBox = new Rectangle(x, y, width / 2, height / 2);
//        instead we should use getBounds to return a new shape every time
    }

    public void remove(){
        App.currentGame.removeFromEntity(this);
    }


    public abstract Shape getBounds();

    public void render(GraphicsContext g){
        g.drawImage(TextureManager.getTexture(textureName), x - width / 2, y - height / 2);
    }

    public abstract void update(float delta);

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
}
