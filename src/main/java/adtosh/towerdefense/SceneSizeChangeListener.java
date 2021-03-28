package adtosh.towerdefense;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class SceneSizeChangeListener implements ChangeListener<Number> {
    private final double ratio;
//    private final Pane contentPane;
    private final String plane;

    public SceneSizeChangeListener(double ratio,   String plane) {
        this.ratio = ratio;
//        this.contentPane = contentPane;
        this.plane = plane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newNumber) {

//        double newValue = newNumber.doubleValue();
//        if (plane.equals("WIDTH")){
//            App.stage.setWidth(newValue *2);
//            App.stage.setHeight((newValue *2 ) *ratio);
//
//        }else {
////            App.stage.setHeight(newValue);
////            App.stage.setWidth(newValue * ratio);
//
//
//        }


        //height / width = ratio
        //height = ratio * width
        //width = height / ratio

    }
}
