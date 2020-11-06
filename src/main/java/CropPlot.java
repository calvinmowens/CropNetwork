package main.java;

import javafx.scene.image.Image;

public class CropPlot {

    ////////////////////////////////////////////////////
    ///////////////////  Variables   ///////////////////
    ////////////////////////////////////////////////////
    private String cropName;
    private Image image;
    private int waterLevel;
    /**
     * 0: empty gauge
     * 1: danger
     * 2: too-low
     * 3: good
     * 4: full
     */
    private int maturity;
    /**
     * Maturity: stage
     * 0: empty plot
     * 1: seeded
     * 2: immature
     * 3: mature
     * 4: dead
     * */


    ////////////////////////////////////////////////////
    ///////////////////  Arrays   //////////////////////
    ////////////////////////////////////////////////////
    private final String[][] cropImgMatrix = {
            {"/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png", "/main/resources/blank.png"},
            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
            {"/main/resources/blank.png", "/main/resources/watermelon_seeded.png", "/main/resources/watermelon_immature.png", "/main/resources/watermelon_mature.png", "/main/resources/dead_watermelon_plot.png"},
            {"/main/resources/blank.png", "/main/resources/onion_seeded.png", "/main/resources/onion_immature.png", "/main/resources/onion_mature.png", "/main/resources/dead_onion_plot.png"},
            {"/main/resources/blank.png", "/main/resources/almond_seeded.png", "/main/resources/potato_immature.png", "/main/resources/potato_mature.png", "/main/resources/dead_potato_plot.png"},
    };

    private final String[] waterImgArray = {
            "/main/resources/water_level_empty.png",
            "/main/resources/water_level_danger.png",
            "/main/resources/water_level_too_low.png",
            "/main/resources/water_level_good.png",
            "/main/resources/water_level_too_full.png"
    };


    ////////////////////////////////////////////////////
    ///////////////////  Constructor   /////////////////
    ////////////////////////////////////////////////////
    /**
     * Just a constructor bro.
     * image and water level NOT set by arguments
     *
     * @param cropName  set cropName to argument
     * @param maturity  set maturity to argument
     */
    public CropPlot(String cropName, int maturity) {
        this.cropName = cropName;
        this.maturity = maturity;
        this.image = getImage();
        this.waterLevel = 3;
    }


    ////////////////////////////////////////////////////
    ///////////////////  Methods   /////////////////////
    ////////////////////////////////////////////////////
    /**
     * Logic around advancing the day based on maturity and water levels.
     *
     * TODO: Add functionality with fertilizers.
     */
    public void nextDayCheck() {
        // change maturity
        if((maturity > 0 && maturity < 3) && (waterLevel > 1)) {
            maturity++;
        } else if((maturity > 0 && maturity < 3) && waterLevel == 1) {
            maturity = 4;
        } else if (maturity == 4) {
            cropName = "Empty Plot";
            waterLevel = 0;
            maturity = 0;
        }
        if (maturity > 0 && maturity <= 3) {
            waterLevel--;
        }
        String imgString = cropImgMatrix[nameToInt()][maturity];
        image = new Image(imgString);
    }

    public void waterCrop() {
        if (maturity != 4) { // if crop is not dead, water
            waterLevel++;
            if (waterLevel > 4) { // if water goes beyond full, kill it
                killCrop();
            }
        }
    }

    public void killCrop() {
        maturity = 4;
        waterLevel = 0;
    }



    ////////////////////////////////////////////////////
    //////////////  Getters & Setters   ////////////////
    ////////////////////////////////////////////////////

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
        int myNameInt = nameToInt();
        String url = cropImgMatrix[myNameInt][maturity];
        return new Image(url);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * This is a method that returns crop name as an int.
     * We use this for displaying the correct crop image based on type and maturity.
     *
     * @return
     */
    public int nameToInt() {
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

}
