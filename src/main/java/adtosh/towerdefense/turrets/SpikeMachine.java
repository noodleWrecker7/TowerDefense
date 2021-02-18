package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.Game;
import adtosh.towerdefense.Placeable;
import adtosh.towerdefense.TextureManager;
import adtosh.towerdefense.entity.projectiles.Rotatable;
import adtosh.towerdefense.levels.Level;
import adtosh.towerdefense.turrets.BaseTurret;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpikeMachine extends BaseTurret  {

    public SpikeMachine(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 100;
        this.TimeTilSpawn = 6d;
//        this.angle = -30;
    }



    @Override
    public void update(float delta) {
        if (!isPlaced())return;
        timeSinceSpawn += delta;
        if (timeSinceSpawn > TimeTilSpawn) {
            timeSinceSpawn = 0;


            //todo split the line up into a bunch of points
            List<Line> options = App.currentGame.getLevel().getPath()
                    .stream()
                    .filter(line -> line.intersects(getRangeBounds().getLayoutBounds()))
                    .collect(Collectors.toList());



            ArrayList<Point2D> points = new ArrayList<>();

            for (Line line: options){
                for (int i = 0; i < App.currentGame.getCanvas().getWidth(); i++) {
                    for (int j = 0; j <App.currentGame.getCanvas().getHeight(); j++) {

                        if (line.contains(i, j) && this.getRangeBounds().contains(i, j)){
                            points.add(new Point2D(i, j));
                        }
                    }
                }
            }

            if (points.size() > 0) {

                Random random = new Random();
                System.out.println(points.size());
                int pointIndex = random.nextInt(points.size());
//                Image spikeImage = TextureManager.getTexture("spike");
                Spike spike = new Spike(points.get(pointIndex).getX()*2 , points.get(pointIndex).getY()*2 , "spike");
                spike.setPlaced(true);
            }
        }
    }
}
