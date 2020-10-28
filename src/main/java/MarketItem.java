package main.java;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class MarketItem {
    private int basePrice;
    private String itemName;
    private String imgUrl;

    public MarketItem(int basePrice, String itemName, String imgUrl) {
        this.basePrice = basePrice;
        this.itemName = itemName;
        this.imgUrl = imgUrl;
    }

    public Image getImage() {
        Image img = new Image(this.imgUrl);
        return img;
    }

    public Label getLabel() {
        Label label = new Label("$" + this.basePrice);
        return label;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
