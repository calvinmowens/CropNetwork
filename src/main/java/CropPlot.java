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
    private int fertilized;

    private boolean pestApplied = false;

    ////////////////////////////////////////////////////
    ///////////////////  Arrays   //////////////////////
    ////////////////////////////////////////////////////
    private final String[][] cropImgMatrix = {
            {"/main/resources/blank.png",
                "/main/resources/blank.png",
                "/main/resources/blank.png",
                "/main/resources/blank.png",
                "/main/resources/blank.png"},
            {"/main/resources/blank.png",
                "/main/resources/corn_seeded.png",
                "/main/resources/corn_immature.png",
                "/main/resources/corn_mature.png",
                "/main/resources/dead_corn_plot.png"},
            {"/main/resources/blank.png",
                "/main/resources/watermelon_seeded.png",
                "/main/resources/watermelon_immature.png",
                "/main/resources/watermelon_mature.png",
                "/main/resources/dead_watermelon_plot.png"},
            {"/main/resources/blank.png",
                "/main/resources/onion_seeded.png",
                "/main/resources/onion_immature.png",
                "/main/resources/onion_mature.png",
                "/main/resources/dead_onion_plot.png"},
            {"/main/resources/blank.png",
                "/main/resources/almond_seeded.png",
                "/main/resources/potato_immature.png",
                "/main/resources/potato_mature.png",
                "/main/resources/dead_potato_plot.png"},
    };

    private final String[] waterImgArray = {
        "/main/resources/water_level_empty.png",
        "/main/resources/water_level_danger.png",
        "/main/resources/water_level_too_low.png",
        "/main/resources/water_level_good.png",
        "/main/resources/water_level_too_full.png"
    };

    private final String[] fertImgArray = {
        "/main/resources/fertilizer_empty.png",
        "/main/resources/fertilizer_level1.png",
        "/main/resources/fertilizer_level2.png",
        "/main/resources/fertilizer_level3.png",
        "/main/resources/fertilizer_full.png"
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
        this.fertilized = 1;
    }


    ////////////////////////////////////////////////////
    ///////////////////  Methods   /////////////////////
    ////////////////////////////////////////////////////
    /**
     * Logic around advancing the day based on maturity and water levels.
     *
     *
     */
    public void nextDayCheck() {
        // change maturity



        if ((maturity > 0 && maturity < 3) && (waterLevel > 1)) {
            if (fertilized > 1 && maturity < 2) {
                maturity++;
            }
            maturity++;
        } else if ((maturity > 0 && maturity < 4) && waterLevel <= 1) {
            killCrop();
        } else if (maturity == 4) {
            cropName = "Empty Plot";
            waterLevel = 0;
            maturity = 0;
            fertilized = 0;
            setPestApplied(false);
        }

        if (maturity > 0 && maturity < 4) {
            waterLevel--;
            if (fertilized > 0) {
                fertilized--;
            }
        }

        String imgString = cropImgMatrix[nameToInt()][maturity];
        image = new Image(imgString);
    }

    public void waterCrop() {
        if (maturity > 0 && maturity < 4) {
            if (waterLevel < 4) {
                waterLevel++;
            } else {
                killCrop();
            }
        }
    }

    public int fertilizeCrop(int count) {
        if (fertilized < 4 && count > 0) {
            fertilized++;
            count--;
        }
        return count;
    }

    public void killCrop() {
        maturity = 4;
        waterLevel = 0;
    }

    public void resetCrop() {
        cropName = "Empty Plot";
        maturity = 0;
        waterLevel = 0;
        fertilized = 0;
        pestApplied = false;
    }

    public void applyPesticide() {
        setPestApplied(true);
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

    public int getMaturity() {
        return maturity;
    };

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public Image getWaterLevelImg() {
        if (waterLevel > 4) {
            waterLevel = 4;
        }
        if (waterLevel < 0) {
            waterLevel = 0;
        }
        String url = waterImgArray[waterLevel];
        return new Image(url);
    }

    public void killPlant() {
        this.setImage(new Image(cropImgMatrix[0][0]));
        this.setMaturity(0);
        this.setWaterLevel(0);
    }

    public Image getFertilizeImg() {
        String url = fertImgArray[fertilized];
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

    public boolean isPestApplied() {
        return pestApplied;
    }

    public void setPestApplied(boolean pestApplied) {
        this.pestApplied = pestApplied;
    }

    public int getFertilized() {
        return fertilized;
    }

    public void setFertilized(int fertilized) {
        this.fertilized = fertilized;
    }


    /**
     * This is a method that returns crop name as an int.
     * We use this for displaying the correct crop image based on type and maturity.
     *
     * @return number corresponding to cropname
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
