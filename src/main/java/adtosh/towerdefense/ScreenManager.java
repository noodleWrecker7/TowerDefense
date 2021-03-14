package adtosh.towerdefense;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class ScreenManager {
    //MY VERSION OF STATE
    static private HashMap<String, Pane> screenMap = new HashMap<>();
    static private Scene main;
    static private String currentPage = null;

    public static void setRootScene(Scene _main) {
        main = _main;
    }

    protected static void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }

    public static String getCurrentPage() {
        return currentPage;
    }

    public static Pane getPane(String name) {
        return screenMap.get(name);
    }

    public static void addRoot(String name, Node root) {
        screenMap.get(name).getChildren().add(root);
    }

    public static void addAllRoots(String name, Node... roots) {
        screenMap.get(name).getChildren().addAll(roots);
    }


    public static Scene getCurrentScene() {
        return main;
    }

    protected static void removeScreen(String name) {
        screenMap.remove(name);
    }



    public static Node getNode(int nodeID){
        return getPane(currentPage).lookup("#" + nodeID);
    }


    // calls enteredPage and leavingPage every time page is changed
    protected static void activate(String name) {

        if (currentPage != null) App.leavingPage(currentPage);
        main.setRoot(screenMap.get(name));
        currentPage = name;
        App.enteringPage(name);
    }


}