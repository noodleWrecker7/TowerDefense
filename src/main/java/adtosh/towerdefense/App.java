package adtosh.towerdefense;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    static void main(String[] args) {
        launch(args);
    }

    private String[] pages = {"menu.fxml"}; // list of fxml pages to be loaded

    @Override
    public void start(Stage stage) throws Exception{
        System.out.println("Wag Wan!");

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        // blank fxml for root
        Parent root = new FXMLLoader().load(classloader.getResourceAsStream("root.fxml"));
        Scene scene = new Scene(root);
        ScreenManager.setRootScene(scene);

        // adds all pages to screen manager
        loadPages(classloader);

        ScreenManager.activate("menu.fxml");

        stage.setScene(scene);
        stage.show();
    }


    private void loadPages(ClassLoader classloader) {
        for (String name : pages) {
            try {
                // loads each page and adds it to screen manager
                ScreenManager.addScreen(name, new FXMLLoader().load(classloader.getResourceAsStream("menu.fxml")));
            } catch (IOException e) {
                System.out.println("Error loading page " + name + " game may be unstable");
            }
        }
    }
}
