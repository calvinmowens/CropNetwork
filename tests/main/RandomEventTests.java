package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.controllers.MainUiController;
import main.java.CropPlot;
import main.java.Game;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertTrue;


public class RandomEventTests extends ApplicationTest {
    @FXML
    private ImageView plot9Image;
    private Pane mainroot;
    private Stage mainstage;
    private Object controller;
    private Game myGame;

    @Override
    public void start(Stage stage) throws IOException {
        mainroot = FXMLLoader.load(Main.class.getResource("screens/welcome_screen.fxml"));
        mainstage = stage;
        mainstage.setTitle("Welcome Screen");
        mainstage.setScene(new Scene(mainroot, 1280, 720));
        mainstage.show();
    }

    @Test
    public void waterlevelRiseOnRain() {
        CropPlot[] cropPlot = new CropPlot[12];
        int[] waterlevels = new int[12];
        for (int i = 0; i < cropPlot.length; i++) {
            Random rand = new Random();
            int cropNum = rand.nextInt(4) + 1;
            int maturityNum = rand.nextInt(3) + 1;
            if (cropNum == 1) {
                cropPlot[i] = new CropPlot("Corn", maturityNum);
            } else if (cropNum == 2) {
                cropPlot[i] = new CropPlot("Watermelon", maturityNum);
            } else if (cropNum == 3) {
                cropPlot[i] = new CropPlot("Onion", maturityNum);
            } else if (cropNum == 4) {
                cropPlot[i] = new CropPlot("Potato", maturityNum);
            }
            waterlevels[i] = cropPlot[i].getWaterLevel();
        }
        MainUiController uiController = new main.controllers.MainUiController();
        uiController.increaseWaterlevelRandomly(cropPlot);
        for (int i = 0; i < waterlevels.length; i++) {
            assertTrue(waterlevels[i] >= cropPlot[i].getWaterLevel());
        }
    }

    @Test
    public void waterlevelFallOnDrought() {
        CropPlot[] cropPlot = new CropPlot[12];
        int[] waterlevels = new int[12];
        for (int i = 0; i < cropPlot.length; i++) {
            Random rand = new Random();
            int cropNum = rand.nextInt(4) + 1;
            int maturityNum = rand.nextInt(3) + 1;
            if (cropNum == 1) {
                cropPlot[i] = new CropPlot("Corn", maturityNum);
            } else if (cropNum == 2) {
                cropPlot[i] = new CropPlot("Watermelon", maturityNum);
            } else if (cropNum == 3) {
                cropPlot[i] = new CropPlot("Onion", maturityNum);
            } else if (cropNum == 4) {
                cropPlot[i] = new CropPlot("Potato", maturityNum);
            }
            waterlevels[i] = cropPlot[i].getWaterLevel();
        }
        MainUiController uiController = new main.controllers.MainUiController();
        uiController.decreaseWaterlevelRandomly(cropPlot);
        for (int i = 0; i < waterlevels.length; i++) {
            assertTrue(waterlevels[i] <= cropPlot[i].getWaterLevel());
        }
    }


}
