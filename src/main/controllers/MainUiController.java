package main.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.CropPlot;
import main.java.Game;
import main.java.InventoryItem;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainUiController implements Initializable {

    @FXML
    private Image useSeedImage;
    @FXML
    private Label daysLabel;
    @FXML
    private ImageView waterLevel_Plot1;
    @FXML
    private ImageView waterLevel_Plot2;
    @FXML
    private ImageView waterLevel_Plot3;
    @FXML
    private ImageView waterLevel_Plot4;
    @FXML
    private ImageView waterLevel_Plot5;
    @FXML
    private ImageView waterLevel_Plot6;
    @FXML
    private ImageView waterLevel_Plot7;
    @FXML
    private ImageView waterLevel_Plot8;
    @FXML
    private ImageView waterLevel_Plot9;
    @FXML
    private ImageView waterLevel_Plot10;
    @FXML
    private ImageView waterLevel_Plot11;
    @FXML
    private ImageView waterLevel_Plot12;

    private ImageView[] waterLevelsArray;

    Map<String, InventoryItem> inventory;
    @FXML
    private AnchorPane inventoryModal;
    @FXML
    private AnchorPane plotPane;
    @FXML
    private ImageView plot2Image;
    @FXML
    private ImageView plot4Image;
    @FXML
    private ImageView plot6Image;
    @FXML
    private ImageView plot9Image;
    @FXML
    private ImageView seedImage;
    @FXML
    private ImageView avatar;
    @FXML
    private Game myGame;
    @FXML
    private AnchorPane background;
    @FXML
    private ImageView backgroundSeason;
    @FXML
    private AnchorPane seedModal;
    @FXML
    private Label money;
    @FXML
    private Label farmName;
    @FXML
    private Label onionCropCounter;
    @FXML
    private Label potatoCropCounter;
    @FXML
    private Label watermelonCropCounter;
    @FXML
    private Label cornCropCounter;
    @FXML
    private Button almondSeedButton;
    @FXML
    private Button onionSeedButton;
    @FXML
    private Button potatoSeedButton;
    @FXML
    private Button watermelonSeedButton;
    @FXML
    private Button cornSeedButton;
    @FXML
    private Button dragonfruitSeedButton;
    @FXML
    private Label watermelonSeedBagCounter;
    @FXML
    private Label almondSeedBagCounter;
    @FXML
    private Label dragonfruitSeedBagCounter;
    @FXML
    private Label potatoSeedBagCounter;
    @FXML
    private Label cornSeedBagCounter;
    @FXML
    private Label onionSeedBagCounter;
    private String selectedSeed;

    private BooleanProperty seedBagClick = new SimpleBooleanProperty(false);
    private BooleanProperty inventoryClick = new SimpleBooleanProperty(false);
    private BooleanProperty backgroundToggle = new SimpleBooleanProperty(false);
    String[] seedImages = {"/main/resources/potatoes.png",
            "/main/resources/watermelon.png", "/main/resources/corn.png",
            "/main/resources/onion.png"};

    /**
     * Create clock method and update time on 10ms basis. Best way to do this?
     * Update elements based on information from Game.java
     */

    /**
     * This method is used to hide certain scene elements prior to page load.
     * @param url The location used to resolve
     *            relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to
     *                       localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initalizable runs");
        seedModal.visibleProperty().bind(seedBagClick);
        background.visibleProperty().bind(backgroundToggle);
        inventoryModal.visibleProperty().bind(inventoryClick);

        waterLevelsArray = new ImageView[] {
                waterLevel_Plot1,
                waterLevel_Plot2,
                waterLevel_Plot3,
                waterLevel_Plot4,
                waterLevel_Plot5,
                waterLevel_Plot6,
                waterLevel_Plot7,
                waterLevel_Plot8,
                waterLevel_Plot9,
                waterLevel_Plot10,
                waterLevel_Plot11,
                waterLevel_Plot12
        };
    }

    /**
     * This method initializes the Game UI with settings from the prev scene
     *
     * @param newGame  param to get information from prev scene
     */
    public void initData(Game newGame) {
        myGame = newGame;

        farmName.setText(myGame.getName());

        if (myGame.getGender().equals("FEMALE")) {
            avatar.setImage(new Image(getClass().
                    getResourceAsStream("/main/resources/female.png")));
        }
        backgroundSeason.setImage(new Image(getClass().
                getResourceAsStream(setStartingSeasonHelper())));

        if(myGame.getInitCounter() == 0) {
            seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));
            selectedSeed = setStartingSeedHelper().substring(0, setStartingSeedHelper().indexOf('.'));
            selectedSeed = selectedSeed.substring(16);
            String firstLetter = selectedSeed.substring(0, 1).toUpperCase();
            selectedSeed = firstLetter + selectedSeed.substring(1);
            myGame.setInitCounter(myGame.getInitCounter() + 1);
//        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper()))); Why is there an extra one here?
        }

        money.setText("$" + Integer.toString(myGame.getMoney()));
        inventory = myGame.getInventoryMap();

        CropPlot[] myPlots = myGame.getPlots();
        for (int i = 0; i < myPlots.length; i++) {
            if (myPlots[i] != null) {
                ((ImageView) plotPane.getChildren().get(i * 2)).setImage(myPlots[i].getImage());
                waterLevelsArray[i].setImage(myPlots[i].getWaterLevelImg());
            }
        }

        // Set seed counters to current inventory count.
        InventoryItem defaultItem = myGame.getDefaultItem();

        cornSeedBagCounter.setText(Integer.toString(inventory.getOrDefault("Corn Seed", defaultItem).getCount()));
        watermelonSeedBagCounter
                .setText(Integer.toString(inventory.getOrDefault("Watermelon Seed", defaultItem).getCount()));
        onionSeedBagCounter.setText(Integer.toString(inventory.getOrDefault("Onion Seed", defaultItem).getCount()));
        potatoSeedBagCounter.setText(Integer.toString(inventory.getOrDefault("Potato Seed", defaultItem).getCount()));

        // Set crop counters to current inventory count.
        cornCropCounter.setText(Integer.toString(inventory.getOrDefault("Corn", defaultItem).getCount()));
        watermelonCropCounter.setText(Integer.toString(inventory.getOrDefault("Watermelon", defaultItem).getCount()));
        onionCropCounter.setText(Integer.toString(inventory.getOrDefault("Onion", defaultItem).getCount()));
        potatoCropCounter.setText(Integer.toString(inventory.getOrDefault("Potato", defaultItem).getCount()));
    }

    public void initPlots() {

    }

    private String setStartingSeasonHelper() {
        String[] seasonImages = {"/main/resources/spring.png",
            "/main/resources/summer.png", "/main/resources/fall.png", "/main/resources/winter.png"};
        switch (myGame.getStartingSeason()) {
        case ("Spring"):
            return seasonImages[0];
        case ("Summer"):
            return seasonImages[1];
        case ("Fall"):
            return seasonImages[2];
        case ("Winter"):
            return seasonImages[3];
        default:
            throw new IllegalStateException("Unexpected value: " + myGame.getStartingSeason());
        }
    }

    private String setStartingSeedHelper() {
        if (myGame.getInitCounter() == 0) {
            switch (myGame.getStartingSeed()) {
                case ("Potato"):
                    return seedImages[0];
                case ("Watermelon"):
                    return seedImages[1];
                case ("Corn"):
                    return seedImages[2];
                case ("Onion"):
                    return seedImages[3];
                default:
                    throw new IllegalStateException("Unexpected value: " + myGame.getStartingSeed());
            }
        } else {
            switch (selectedSeed) {
                case ("Potato"):
                    return seedImages[0];
                case ("Watermelon"):
                    return seedImages[1];
                case ("Corn"):
                    return seedImages[2];
                case ("Onion"):
                    return seedImages[3];
                default:
                    throw new IllegalStateException("Unexpected value: " + myGame.getStartingSeed());
            }
        }
    }

    public void toggleSeedModal(ActionEvent actionEvent) {
        seedBagClick.set(!seedBagClick.get());
        backgroundToggle.set(!backgroundToggle.get());
    }

    public void toggleInventoryModal(ActionEvent actionEvent) {
        inventoryClick.set(!inventoryClick.get());
        backgroundToggle.set(!backgroundToggle.get());
    }

    public void closeModals(MouseEvent mouseEvent) {
        inventoryClick.set(false);
        seedBagClick.set(false);
        backgroundToggle.set(false);
    }

    public void openMarket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/market_ui.FXML"));
        Parent openMarket = loader.load();
        Scene startOpenMarket = new Scene(openMarket);

        MarketUiController controller = loader.getController();
        controller.initData(myGame);
        // Stage and show the new scene
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Buy");
        window.setScene(startOpenMarket);
        window.show();
    }

    public void toolHarvestClick(ActionEvent actionEvent) {
        if (myGame.getPlotClickMode() == null || !(myGame.getPlotClickMode().equals("Harvest"))) {
            myGame.setPlotClickMode("Harvest");
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(new ImageCursor(new Image("/main/resources/fork_cursor.png")));
        } else {
            myGame.setPlotClickMode(null);
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.DEFAULT);
        }
    }

    public void toolWaterClick(ActionEvent actionEvent) {
        if (myGame.getPlotClickMode() == null || !(myGame.getPlotClickMode().equals("Water"))) {
            myGame.setPlotClickMode("Water");
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(new ImageCursor(new Image("/main/resources/water_cursor.png")));
        } else {
            myGame.setPlotClickMode(null);
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.DEFAULT);
        }
    }

    /**
     * We can use this to fix our bug on updating inventory modal when harvesting
     */
//    public void updateCount() {
//        cornCropCounter.setText(Integer.toString(myGame.getInventory().getCornCount()));
//        watermelonCropCounter.setText(Integer.toString(myGame.getInventory().getWatermelonCount()));
//        onionCropCounter.setText(Integer.toString(myGame.getInventory().getOnionCount()));
//        potatoCropCounter.setText(Integer.toString(myGame.getInventory().getPotatoCount()));
//    }

    /**
     * TODO: Implement watering feature
     * @param actionEvent
     */
    public void plotClickedHandler(ActionEvent actionEvent) {
        System.out.println("Plot clicked: " + myGame.getPlotClickMode());
        String id = ((Node) actionEvent.getSource()).getId();
        String mode = myGame.getPlotClickMode();
        // Based on the current plotClickMode in Game.java the plots will behave in a certain way.
        if (mode != null) {
            if (mode.equals("Harvest")) {
                harvestCrop(id);
            } else if (mode.equals("Water")) {
                waterCrop(id);
            } else if (mode.equals("Seed")) {
                plantCrop(id);
            }
        }
    }

    private void plantCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        Map<String, InventoryItem> map = myGame.getInventoryMap();

        if(myPlots[plotId] != null) {
            if(myPlots[plotId].getMaturity() == 0) {
                InventoryItem item = map.get(selectedSeed);
                if(item.getCount() > 0) {
                    item.setCount(item.getCount() - 1);
                    myPlots[plotId].setCropName(selectedSeed);
                    myPlots[plotId].setMaturity(1);
                    myPlots[plotId].setWaterLevel(2);

                }
            }
        }
        System.out.println(myPlots[plotId].getMaturity() + ", " + myPlots[plotId].getCropName());
        System.out.println(map.get(selectedSeed).getCount());
        initData(myGame);
    }

    public void harvestCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        InventoryItem defaultItem = myGame.getDefaultItem();
        Map<String, InventoryItem> map = myGame.getInventoryMap();
        CropPlot myCrop = myPlots[plotId];
        // if plot is not empty and fully mature, we can harvest
        if (myPlots[plotId] != null) {
            if (myPlots[plotId].getMaturity() == 3) {
                String cropName = myCrop.getCropName();
                InventoryItem item = map.get(cropName);
                item.setCount(item.getCount() + 5);
                myPlots[plotId].setCropName("Empty Plot");
                myPlots[plotId].setMaturity(0);
                myPlots[plotId].setWaterLevel(0);
            } else if (myPlots[plotId].getMaturity() == 4) {
                myPlots[plotId].setCropName("Empty Plot");
                myPlots[plotId].setMaturity(0);
                myPlots[plotId].setWaterLevel(0);
            }
        }
        // Why is the below line needed? I'm not sure what it's doing here.
//        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));
        this.initData(myGame); // should we change these to an update method? how taxing is running initData?
    }

    public void waterCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;
        CropPlot myCrop = myPlots[plotId];
        if (myCrop.getMaturity() != 4) { // if crop is not dead, water
            myCrop.setWaterLevel(myCrop.getWaterLevel() + 1);
            if (myCrop.getWaterLevel() == 4) { // if water goes beyond full, kill it
                killCrop(myCrop);
            }
        }
        this.initData(myGame);
    }

    public void killCrop(CropPlot myCrop) {
        myCrop.setMaturity(4);
        myCrop.setWaterLevel(0);
    }

    public void nextDay(ActionEvent actionEvent) {
        for (CropPlot plot : myGame.getPlots()) {
            plot.nextDayCheck();
            if(!(plot.getCropName().equals("Empty Plot"))) {
                plot.setWaterLevel(plot.getWaterLevel() - 1);
            }
        }
        myGame.setDay(myGame.getDay() + 1);
        daysLabel.setText("Day " + myGame.getDay());
        this.initData(myGame);
    }

    public void seedClick(ActionEvent mouseEvent) {
        if (myGame.getPlotClickMode() == null || !(myGame.getPlotClickMode().equals("Seed"))) {
            myGame.setPlotClickMode("Seed");
            Scene myScene = ((Node) mouseEvent.getSource()).getScene();
            myScene.setCursor(Cursor.CROSSHAIR);
        } else {
            myGame.setPlotClickMode(null);
            Scene myScene = ((Node) mouseEvent.getSource()).getScene();
            myScene.setCursor(Cursor.DEFAULT);
        }
    }

    public void changeSeed(ActionEvent actionEvent) {
        String buttonClicked = ((Node) actionEvent.getSource()).getId();
        buttonClicked = buttonClicked.substring(0, buttonClicked.indexOf('B'));
        selectedSeed = buttonClicked;
        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));
    }
}
