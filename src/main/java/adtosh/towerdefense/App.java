package adtosh.towerdefense;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {


    static void main(String[] args) {
        launch(args);
    }

    private String[] pages = {"menu.fxml", "game.fxml"}; // list of fxml pages to be loaded

    public static Game currentGame;
    public static ClassLoader classloader;






    @Override
    public void start(Stage stage) throws Exception {

        classloader = Thread.currentThread().getContextClassLoader();
        // blank fxml for root why tho?
//        Parent root = new FXMLLoader().load(classloader.getResourceAsStream("root.fxml"));
        Pane root = new Pane();
        Scene scene = new Scene(root);
        ScreenManager.setRootScene(scene);
        ScreenManager.setStage(stage);
//        // adds all pages to screen manager
        loadPages(classloader);
        TextureManager.init();
        ScreenManager.activate("menu.fxml");
        stage.setScene(scene);
        stage.show();



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
