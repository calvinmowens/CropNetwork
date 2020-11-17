//package main;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import main.controllers.MainUiController;
//import main.java.CropPlot;
//import main.java.Game;
//import main.java.InventoryItem;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Random;
//import static org.junit.Assert.assertTrue;
//
//
//public class RandomEventTests extends ApplicationTest {
//    @FXML
//    private ImageView plot9Image;
//    private Pane mainroot;
//    private Stage mainstage;
//    private Object controller;
//    private Game myGame;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        mainroot = FXMLLoader.load(Main.class.getResource("screens/welcome_screen.fxml"));
//        mainstage = stage;
//        mainstage.setTitle("Welcome Screen");
//        mainstage.setScene(new Scene(mainroot, 1280, 720));
//        mainstage.show();
//    }
//    @Test
//    public void fertilizedCropHaveHigherYield() {
//        Game myGame = new Game();
//        myGame.setGender("FEMALE");
//        myGame.setStartingSeason("Spring");
//        myGame.setCurrentSeed("Corn");
//
//        CropPlot[] myPlots = myGame.getPlots();
//        for (int i = 0; i < 12; i++) {
//            myPlots[i] = new CropPlot("Corn", 3);
//            myPlots[i].setFertilized(4);
//        }
//        MainUiController mainUiController = new MainUiController();
//        mainUiController.initData(myGame);
//        Map<String, InventoryItem> myMap = myGame.getInventoryMap();
//        myMap.put("Corn",
//                new InventoryItem(10, "Corn",
//                        "/main/resources/cornBag.png", 0));
//        System.out.println("Test: " + myMap.get("Corn").getCount());
//        mainUiController.harvestCrop("plot1");
//        System.out.println("Test: " + myMap.get("Corn").getCount());
//        System.out.println("Test: " + myGame.getInventoryMap().get("Corn").getCount());
//        assertTrue(myGame.getInventoryMap().get("Corn").getCount() > 5);
//
//    }
//
//
//    @Test
//    public void pesticideAppearsOnScreenWhenClicked() throws InterruptedException {
//        clickOn("#startGameBtn");
//        moveTo("#name").clickOn("#name").write("1");
//        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
//        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
//        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
//        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
//        moveTo("#startGameBtn").clickOn("#startGameBtn");
//        moveTo("#market").clickOn("#market");
//        moveTo("#marketItem6").clickOn("#marketItem6");
//        moveTo("#selectedItemQuantity").clickOn("#selectedItemQuantity").write("12");
//        moveTo("#buyItemBtn").clickOn("#buyItemBtn");
//        moveTo("#exit").clickOn("#exit");
//        moveTo("#pesticide").clickOn("#pesticide");
//        moveTo("#plot12").clickOn("#plot12");
//        moveTo("#pesticide").clickOn("#pesticide");
//        AnchorPane plots = (AnchorPane) (mainstage.getScene()
//                .getRoot().getChildrenUnmodifiable().get(1));
//        ImageView plot12pesticide = (ImageView) plots.getChildren().get(47);
//        assertTrue(plot12pesticide.getImage().getUrl().contains("/main/resources/Pesticide.png"));
//    }
//
//
//    @Test
//    public void dontKillIfPesticized() {
//        CropPlot[] cropPlot = new CropPlot[12];
//        int[] waterlevels = new int[12];
//        for (int i = 0; i < cropPlot.length; i++) {
//            cropPlot[i] = new CropPlot("Corn", 2);
//            cropPlot[i].setPestApplied(true);
//        }
//        MainUiController uiController = new main.controllers.MainUiController();
//        uiController.killPlantsRandomly(cropPlot);
//        for (int i = 0; i < waterlevels.length; i++) {
//            System.out.println(cropPlot[i].getImage().getUrl());
//            assertTrue(
//                    cropPlot[i].getImage()
//                            .getUrl().contains("/main/resources/corn_immature.png"));
//        }
//    }
//
//
//    @Test
//    public void waterlevelRiseOnRain() {
//        CropPlot[] cropPlot = new CropPlot[12];
//        int[] waterlevels = new int[12];
//        for (int i = 0; i < cropPlot.length; i++) {
//            Random rand = new Random();
//            int cropNum = rand.nextInt(4) + 1;
//            int maturityNum = rand.nextInt(3) + 1;
//            if (cropNum == 1) {
//                cropPlot[i] = new CropPlot("Corn", maturityNum);
//            } else if (cropNum == 2) {
//                cropPlot[i] = new CropPlot("Watermelon", maturityNum);
//            } else if (cropNum == 3) {
//                cropPlot[i] = new CropPlot("Onion", maturityNum);
//            } else if (cropNum == 4) {
//                cropPlot[i] = new CropPlot("Potato", maturityNum);
//            }
//            waterlevels[i] = cropPlot[i].getWaterLevel();
//        }
//        MainUiController uiController = new main.controllers.MainUiController();
//        uiController.increaseWaterlevelRandomly(cropPlot);
//        for (int i = 0; i < waterlevels.length; i++) {
//            assertTrue(waterlevels[i] <= cropPlot[i].getWaterLevel());
//        }
//    }
//
//    @Test
//    public void waterlevelFallOnDrought() {
//        CropPlot[] cropPlot = new CropPlot[12];
//        int[] waterlevels = new int[12];
//        for (int i = 0; i < cropPlot.length; i++) {
//            Random rand = new Random();
//            int cropNum = rand.nextInt(4) + 1;
//            int maturityNum = rand.nextInt(3) + 1;
//            if (cropNum == 1) {
//                cropPlot[i] = new CropPlot("Corn", maturityNum);
//            } else if (cropNum == 2) {
//                cropPlot[i] = new CropPlot("Watermelon", maturityNum);
//            } else if (cropNum == 3) {
//                cropPlot[i] = new CropPlot("Onion", maturityNum);
//            } else if (cropNum == 4) {
//                cropPlot[i] = new CropPlot("Potato", maturityNum);
//            }
//            waterlevels[i] = cropPlot[i].getWaterLevel();
//        }
//        MainUiController uiController = new main.controllers.MainUiController();
//        uiController.decreaseWaterlevelRandomly(cropPlot);
//        for (int i = 0; i < waterlevels.length; i++) {
//            assertTrue(waterlevels[i] >= cropPlot[i].getWaterLevel());
//        }
//    }
//
//
//}
