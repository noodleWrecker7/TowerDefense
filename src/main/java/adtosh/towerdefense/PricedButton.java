package adtosh.towerdefense;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

//@DefaultProperty(value = "children")
public class PricedButton extends Button implements ConfigNode {

    private int price;


    public PricedButton() {

    }



    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;

    }

}
interface ConfigNode {
    public int getPrice();
    public void setPrice(int price);
}


