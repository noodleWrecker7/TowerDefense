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
        loadImage("spike", "Spikes.png");
        loadImage("wizard", "wizard2.png", 150, 0, true);
        loadImage("super monkey", "SuperMonkey2.png", 150, 0, true);
        loadImage("spike machine", "spike-machine2.png", 90, 0, true);
        loadImage("machine gun", "machine-gun.png", 120, 0 , true);
        loadImage("multi shooter", "multi-shooter.png", 120, 0, true);
        loadImage("dart monkey", "dart-monkey.png", 100, 0, true);
        loadImage("cannon", "cannon.png", 90, 0 , true);




        loadImage("magic ball", "001-WizardProjectile.png", 100, 0 , true);
        loadImage("dart", "dart.png", 85, 0 , true);
        loadImage("small dart", "dart.png", 70, 0, true);
        loadImage("big dart", "dart.png", 100, 0, true);

        loadImage("missile", "missile.png", 70, 0, true);

        loadImage("explosion", "explosion.png", 105, 0, true);



        // loads the balloon 50 pixels wide and keeps the aspect ratio

//        loadImage("balloon-0", "balloon-0.png", 50, 0, true);
        System.out.println("loaded balloon-0");


        for (int i = 0; i < 6; i++) {
            loadImage("balloon-"+i, "balloon-"+i+".png", 50, 0, true);
        }
        loadImage("balloon-6", "balloon-6.png", 40, 0, true);

        for (int i = 1; i <= 11 ; i++) {
            loadImage("spikes-"+i, "spikes-"+i+".png");
        }
    }

}
