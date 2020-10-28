package main.java;

//import com.sun.scenario.effect.Crop;
import javafx.scene.image.Image;

public class CropPlot {
    private String imgUrl;

    private int waterLevel;
    /**
     * Maturity: stage
     * 0: empty plot
     * 1: seeded
     * 2: immature
     * 3: mature
     * 4: dead
     * */
    private int maturity;

    public void checkMaturity() {
        if (this.maturity == 1 || (maturity == 2 && waterLevel > 2)) {
            this.maturity++;
        } else if (maturity == 2) {
            this.maturity += 2;
        }
    }
    private String cropName;
    private Image image;

    public boolean isPlanted() {
        return planted;
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    private boolean planted;

    /**
     * TODO: Update imgUrl with the right url
     * right now they are all corn images. Row 1 should represent corn images, row 2 should represent onions, etc...
     *
     */
//    private final String[][] imgMatrix = {
//            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
//            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
//            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
//            {"/main/resources/blank.png", "/main/resources/corn_seeded.png", "/main/resources/corn_immature.png", "/main/resources/corn_mature.png", "/main/resources/dead_corn_plot.png"},
//    };

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
    public CropPlot(String cropName, int maturity) {

    }

}
