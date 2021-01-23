package adtosh.towerdefense.levels;

import adtosh.towerdefense.entity.Balloon;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

// level class, stores data about map, current wave, lives etc
public class Level {

    private int wave;
    private int lives;
    private Balloon[] balloons;

    private int[][] mapPath;
    private Path path;

    public void loadPath(int level) {
        mapPath = LevelPaths.paths[level];

        // need to make balloons follow this

        path = new Path();
        MoveTo start = new MoveTo(mapPath[0][0], mapPath[0][1]);
        path.getElements().add(start);

        for (int i = 1; i < mapPath.length; i++) {
            LineTo line = new LineTo(mapPath[i][0], mapPath[i][1]);
            path.getElements().add(line);
        }
    }

    // debug method
    public void drawPath(GraphicsContext g) {
//        System.out.println("path");

        // below is temporary for debugging
        g.setStroke(Color.GREEN);
        g.setLineWidth(4);
        g.beginPath();

        g.moveTo(mapPath[0][0], mapPath[0][1]);

        for (int i = 1; i < mapPath.length; i++) {
            g.lineTo(mapPath[i][0], mapPath[i][1]);
        }
        g.stroke();

    }

}
