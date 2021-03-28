package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.Game;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpikeMachine extends BaseTurret {

    public SpikeMachine(double x, double y, String texture) {
        super(x, y, texture);
        this.range = 150;
        this.timeTilSpawn = 6d;
        this.value= 1500;
        initialiseUpgrades();


    }

    @Override
    protected void initialiseUpgrades() {
        Upgrade upgrade1 = new Upgrade("increase range", 600, this);
        upgrade1.createUpgrade(() -> {
            this.range += 70;
        });
        this.addUpgradeList1(upgrade1);

        Upgrade upgrade2 = new Upgrade("faster spike production", 700, this);
        upgrade2.createUpgrade(() -> {
            this.timeTilSpawn -= 0.5;
        });
        this.addUpgradeList2(upgrade2);

        Upgrade upgrade3 = new Upgrade("super fast spike production", 800, this);
        upgrade3.createUpgrade(() -> { this.timeTilSpawn -= 0.5; });
        this.addUpgradeList2(upgrade3);


    }

    @Override
    public void update(double delta) {
//        System.out.println(timeTilSpawn);
        if (!isPlaced()) return;
        if (App.currentGame.getCurrentState() == Game.GameState.ROUND_INACTIVE) return;
        timeSinceSpawn += delta;
        if (timeSinceSpawn > timeTilSpawn) {
            timeSinceSpawn = 0;


            List<Line> options = App.currentGame.getLevel().getPath()
                    .stream()
                    .filter(line -> line.intersects(getRangeBounds().getLayoutBounds()))
                    .collect(Collectors.toList());


            ArrayList<Point2D> points = new ArrayList<>();


            for (Line line : options) {
                double minX = Math.min(line.getStartX(), line.getEndX());
                double maxX = Math.max(line.getStartX(), line.getEndX());

                double minY = Math.min(line.getStartY(), line.getEndY());
                double maxY = Math.max(line.getStartY(), line.getEndY());




                for (double x = minX; x <= maxX; x++) {
                    for (double y = minY; y <= maxY; y++) {
                        //flip sign if other way rounds
                        if (this.getRangeBounds().contains(x, y)) {
                            points.add(new Point2D(x, y));
                            System.out.println("ADED");
                        }


                    }

                }

//                for (int i = 0; i < App.currentGame.getCanvas().getWidth(); i++) {
//                    for (int j = 0; j <App.currentGame.getCanvas().getHeight(); j++) {
//
//                        if (line.contains(i, j) && this.getRangeBounds().contains(i, j)){
//                            points.add(new Point2D(i, j));
//                        }
//                    }
//                }
            }

            if (points.size() > 0) {

                Random random = new Random();
                int pointIndex = random.nextInt(points.size());
                Spike spike = new Spike(points.get(pointIndex).getX()*2, points.get(pointIndex).getY()*2, "spike");
                spike.setPlaced(true);
            }
        }
    }

    private boolean checkInRange(double x, double y) {
        double distanceX = this.getX() - x;
        double distanceY = this.getY() - y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY) <= range;

    }
}
