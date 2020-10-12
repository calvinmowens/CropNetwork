package main.java;

import javafx.scene.image.Image;

import java.util.HashMap;

public class CropPlot {
    private String imgUrl;
    /**
     * Maturity: meaning
     * 0: empty plot
     * 1: seeded
     * 2: immature
     * 3: mature
     * */
    private int maturity;
    private String cropName;
    private Image image;

    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getMaturity() {
        return maturity;
    }

    public String getCropName() {
        return cropName;
    }

    public Image getImage() {
        return image;
    }

    public CropPlot(Crop crop, int maturity) {
        this.cropName = cropName;
        this.maturity = maturity;
        this.imgUrl = imgUrl;
    }

}
