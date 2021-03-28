package adtosh.towerdefense;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {


    static void main(String[] args) {
        launch(args);
    }

    private String[] pages = {"menu.fxml", "game.fxml"}; // list of fxml pages to be loaded

    public static Game currentGame;
    public static ClassLoader classloader;



    //todo is there a way to make a canvas in fxml and set dimensions
    private ChangeListener<? super Number> widthChangeListener;
    private ChangeListener<? super Number> heightChangeListener;


    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Wag Wan!");

        classloader = Thread.currentThread().getContextClassLoader();
        // blank fxml for root why tho?
        Parent root = new FXMLLoader().load(classloader.getResourceAsStream("root.fxml"));
        Scene scene = new Scene(root);
        ScreenManager.setRootScene(scene);
//        // adds all pages to screen manager
        loadPages(classloader);
        TextureManager.init();
        ScreenManager.activate("menu.fxml");
        stage.setScene(scene);
        stage.show();

        double ratio = stage.getWidth()/stage.getHeight();
        System.out.println(ratio);


        widthChangeListener = ((observable, oldValue, newValue) -> {

            stage.heightProperty().removeListener(heightChangeListener);

            stage.setHeight(newValue.doubleValue() / 2.0);
            System.out.println("WIDTH");
            stage.heightProperty().addListener(heightChangeListener);
        });
        heightChangeListener = (observable, oldValue, newValue) -> {

            stage.widthProperty().removeListener(widthChangeListener);
            stage.setWidth(newValue.doubleValue()* 2);
            System.out.println("HEIGHT");
            stage.widthProperty().addListener(widthChangeListener);
        };
//        SceneSizeChangeListener width = new SceneSizeChangeListener(ratio, "WIDTH");
        stage.widthProperty().addListener(widthChangeListener);
        stage.heightProperty().addListener(heightChangeListener);



        // todo decide what level to load

    }



    // called every time a new page is switched to
    public static void enteringPage(String name) {

    }

    // called every time a page is left
    public static void leavingPage(String name) {

    }


    private void loadPages(ClassLoader classloader) {

        for (String name : pages) {
            try {
                // loads each page and adds it to screen manager
                ScreenManager.addScreen(name, new FXMLLoader(getClass().getResource("/game.fxml")).load(classloader.getResourceAsStream(name)));
            } catch (IOException e) {
                System.out.println("Error loading page " + name + " game may be unstable");
//                System.out.println(e.toString());
                for (StackTraceElement line: e.getStackTrace()) {
                    System.out.println(line.toString());
                }

                System.exit(1);
            }
        }
    }
}
