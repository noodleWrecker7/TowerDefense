package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpikeMachine extends BaseTurret  {

    public SpikeMachine(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 100;
        this.timeTilSpawn = 6d;
        initialiseUpgrades();



    }

    @Override
    protected void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 600);
        upgrade1.createUpgrade(() ->{ this.range +=70; });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("faster spike production", 700);
        upgrade2.createUpgrade(() ->{ this.timeTilSpawn -= 0.5; });
        this.addUpgradeList2(upgrade2);

        Upgrade upgrade3 = new Upgrade("super fast spike production", 800);
        upgrade2.createUpgrade(() ->{ this.timeTilSpawn -= 0.5; });
        this.addUpgradeList2(upgrade3);






    }

    @Override
    public void update(double delta) {
        System.out.println(timeTilSpawn);
        if (!isPlaced())return;
        timeSinceSpawn += delta;
        if (timeSinceSpawn > timeTilSpawn) {
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
                int pointIndex = random.nextInt(points.size());
                Spike spike = new Spike(points.get(pointIndex).getX()*2 , points.get(pointIndex).getY()*2 , "spike");
                spike.setPlaced(true);
            }
        }
    }
}
