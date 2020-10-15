package main.java;

import javafx.scene.image.Image;

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

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public int getMaturity() {
        return maturity;
    }

    public String getCropName() {
        return cropName;
    }

    public Image getImage() {
        return new Image(this.imgUrl);
    }

    public CropPlot(String cropName, int maturity, String imgUrl) {
        this.cropName = cropName;
        this.maturity = maturity;
        this.imgUrl = imgUrl;
    }

}
