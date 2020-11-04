package main.java;

import javafx.scene.image.Image;

public class CropPlot {

    private String cropName;

    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;
    private int waterLevel;
    private int maturity;
    /**
     * Maturity: stage
     * 0: empty plot
     * 1: seeded
     * 2: immature
     * 3: mature
     * 4: dead
     * */

    /**
     * TODO: Update imgUrl with the right url
     * right now they are all corn images. Row 1 should represent corn images, row 2 should represent onions, etc...
     *
     */
    private final String[][] cropImgMatrix = {
            {"/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png"},
            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
            {"/main/resources/blank.png", "/main/resources/watermelon_seeded.png", "/main/resources/watermelon_immature.png", "/main/resources/watermelon_mature.png", "/main/resources/dead_watermelon_plot.png"},
            {"/main/resources/blank.png", "/main/resources/onion_seeded.png", "/main/resources/onion_immature.png", "/main/resources/onion_mature.png", "/main/resources/dead_onion_plot.png"},
            {"/main/resources/blank.png", "/main/resources/almond_seeded.png", "/main/resources/potato_immature.png", "/main/resources/potato_mature.png", "/main/resources/dead_potato_plot.png"},
    };

    private final String[] waterImgArray = {
            "/main/resources/water_level_danger.png",
            "/main/resources/water_level_too_low.png",
            "/main/resources/water_level_good.png",
            "/main/resources/water_level_too_full.png"
    };

    public CropPlot(String cropName, int maturity) {
        this.cropName = cropName;
        this.maturity = maturity;
        this.image = getImage();
        this.waterLevel = 2;
    }

    public void nextDayCheck() {
        if((maturity == 1 && waterLevel == 2)|| (maturity == 2 && waterLevel == 2)) {
            maturity++;
        } else if(maturity == 4) {
            maturity = 0;
            cropName = "Empty Plot";
        } else if (maturity == 2 || maturity == 3 || maturity == 1) {
            maturity = 4;
        }
        String imgString = cropImgMatrix[nameToInt(cropName)][maturity];
        image = new Image(imgString);
    }

    public int nameToInt(String cropName) {
        switch (cropName) {
            case "Corn":
                return 1;
            case "Watermelon":
                return 2;
            case "Onion":
                return 3;
            case "Potato":
                return 4;
            default:
                return 0;
        }
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }

    public int getMaturity() { return maturity; };

    public void setWaterLevel(int waterLevel) { this.waterLevel = waterLevel; }

    public int getWaterLevel() { return waterLevel; }

    public Image getWaterLevelImg() {
        String url = waterImgArray[waterLevel];
        return new Image(url);
    }

    public Image getImage() {
        int myNameInt = nameToInt(cropName);
        String url = cropImgMatrix[myNameInt][maturity];
        return new Image(url);
    }

}
