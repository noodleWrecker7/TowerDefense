package adtosh.towerdefense;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenManager {
    static private HashMap<String, Pane> screenMap = new HashMap<>();
    static private Scene main;

    public static void setRootScene(Scene _main) {
        main = _main;
    }

    protected static void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    public static Pane getPane(String name) {
        return screenMap.get(name);
    }

    public static Scene getCurrentScene() {
        return main;
    }

    protected static void removeScreen(String name) {
        screenMap.remove(name);
    }

    protected static void activate(String name) {
        main.setRoot(screenMap.get(name));
    }
}