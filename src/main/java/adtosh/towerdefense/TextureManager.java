package adtosh.towerdefense;

import javafx.scene.image.Image;

import java.util.HashMap;


// so you hadn't added your Assets class to the repo so I've created this which
// is what i think you were kind of making anyway
public class TextureManager {

    private static HashMap<String, Image> images = new HashMap<>();

    public static Image getTexture(String name) {
        return images.get(name);
    }

    // used to scale an image when its loaded
    public static void loadImage(String name, String file, int width, int height, boolean ratio) {
        images.put(name, new Image(file, width, height, ratio, false));
    }

    public static void loadImage(String name, String file) {
        images.put(name, new Image(file));
    }

    public static void init() {
        loadImage("grass", "grass.png");
        System.out.println("loaded grass");

        // loads the balloon 50 pixels wide and keeps the aspect ratio
        loadImage("balloon", "balloon.png", 50, 0, true);
        System.out.println("loaded balloon");

        loadImage("balloon-0", "balloon-0.png", 50, 0, true);
        System.out.println("loaded balloon-0");
    }

}
