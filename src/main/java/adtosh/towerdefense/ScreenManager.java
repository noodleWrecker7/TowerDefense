package adtosh.towerdefense;

import adtosh.towerdefense.entity.Balloon;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScreenManager {
    //MY VERSION OF STATE
    static private HashMap<String, Pane> screenMap = new HashMap<>();
    static private Scene main;
    static private String currentPage = null;
    static private Stage stage;

    public static void setRootScene(Scene _main) {
        main = _main;
    }
    public static void setStage(Stage _stage){
        stage = _stage;
    }
    public static void resizeStage(int width, int height){
        stage.setWidth(width);
        stage.setHeight(height);
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

    public static void addNode(String name, Node node) {
        screenMap.get(name).getChildren().add(node);
    }

    public static void removeNode(String name, Node node) {
        screenMap.get(name).getChildren().remove(node);
    }

    public static void addAllRoots(String name, Node... roots) {
        screenMap.get(name).getChildren().addAll(roots);
    }
//    public static void removeAllNodes(String name){
//        screenMap.get(name).getChildren().removeAll(screenMap.get(name).getChildren());
//    }
    public static  void retainNodes(String name, Class<?> t){
        screenMap.get(name).getChildren().removeIf(node -> node instanceof Line );
    }


    public static Scene getCurrentScene() {
        return main;
    }

    protected static void removeScreen(String name) {
        screenMap.remove(name);
    }

    public static Node getNode(String nodeID){
        return getPane(currentPage).lookup("#" + nodeID);

    }
    public static Node getNode(String nodeID, String page){
        return getPane(page).lookup("#" + nodeID);

    }



//    public static Node getNode(int nodeID){
//        return getPane(currentPage).lookup("#" + nodeID);
//    }


    // calls enteredPage and leavingPage every time page is changed
    public static void activate(String name) {



        if (currentPage != null) App.leavingPage(currentPage);
        Pane pane = screenMap.get(name);
//        main.setRoot(screenMap.get(name));
        main.setRoot(pane);
        stage.setHeight(pane.getPrefHeight());
        stage.setWidth(pane.getPrefWidth());

        currentPage = name;
        App.enteringPage(name);


    }




}