/**
 * This class will be to store market and inventory items that will display in the market.
 */

package main.java;

import javafx.scene.image.Image;

public class Item {

    private String name;
    private Image image;

    public Item(String name, Image image) {
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
