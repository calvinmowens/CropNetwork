package main.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.CropPlot;
import main.java.Game;
import main.java.InventoryItem;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class MainUiController implements Initializable {

    ////////////////////////////////////////////////////
    /////////////////  Variables   /////////////////////
    ////////////////////////////////////////////////////
    // non-FXML
    private static Game myGame;
    private ImageView[] waterLevelsArray;
    private ImageView[] fertLevelsArray;
    private ImageView[] pesticideArray;
    private Map<String, InventoryItem> inventory;
    private String currentSeed = "Corn";

    // backgrounds
    @FXML private AnchorPane background;
    @FXML private ImageView backgroundSeason = new ImageView();

    // top left menu
    @FXML private ImageView avatar = new ImageView();
    @FXML private Label farmName = new Label("Cattle Farm");
    @FXML private Label money = new Label("10000");
    @FXML private Label daysLabel;

    @FXML private AnchorPane plotPane;
    @FXML private ImageView seedImage = new ImageView();
    @FXML private ImageView harvestToolImage = new ImageView();
    @FXML private ImageView waterToolImage = new ImageView();

    // water levels
    @FXML private ImageView plot1WaterLevel = new ImageView();
    @FXML private ImageView plot2WaterLevel = new ImageView();
    @FXML private ImageView plot3WaterLevel = new ImageView();
    @FXML private ImageView plot4WaterLevel = new ImageView();
    @FXML private ImageView plot5WaterLevel = new ImageView();
    @FXML private ImageView plot6WaterLevel = new ImageView();
    @FXML private ImageView plot7WaterLevel = new ImageView();
    @FXML private ImageView plot8WaterLevel = new ImageView();
    @FXML private ImageView plot9WaterLevel = new ImageView();
    @FXML private ImageView plot10WaterLevel = new ImageView();
    @FXML private ImageView plot11WaterLevel = new ImageView();
    @FXML private ImageView plot12WaterLevel = new ImageView();

    // fertilizer levels
    @FXML private ImageView plot1FertLevel = new ImageView();
    @FXML private ImageView plot2FertLevel = new ImageView();
    @FXML private ImageView plot3FertLevel = new ImageView();
    @FXML private ImageView plot4FertLevel = new ImageView();
    @FXML private ImageView plot5FertLevel = new ImageView();
    @FXML private ImageView plot6FertLevel = new ImageView();
    @FXML private ImageView plot7FertLevel = new ImageView();
    @FXML private ImageView plot8FertLevel = new ImageView();
    @FXML private ImageView plot9FertLevel = new ImageView();
    @FXML private ImageView plot10FertLevel = new ImageView();
    @FXML private ImageView plot11FertLevel = new ImageView();
    @FXML private ImageView plot12FertLevel = new ImageView();

    // pesticide images
    @FXML private ImageView plot1Pesticide = new ImageView();
    @FXML private ImageView plot2Pesticide = new ImageView();
    @FXML private ImageView plot3Pesticide = new ImageView();
    @FXML private ImageView plot4Pesticide = new ImageView();
    @FXML private ImageView plot5Pesticide = new ImageView();
    @FXML private ImageView plot6Pesticide = new ImageView();
    @FXML private ImageView plot7Pesticide = new ImageView();
    @FXML private ImageView plot8Pesticide = new ImageView();
    @FXML private ImageView plot9Pesticide = new ImageView();
    @FXML private ImageView plot10Pesticide = new ImageView();
    @FXML private ImageView plot11Pesticide = new ImageView();
    @FXML private ImageView plot12Pesticide = new ImageView();

    // rain animation + popups + labels
    @FXML private ImageView rainAnimation;
    @FXML private AnchorPane locustPopup;
    @FXML private AnchorPane rainPopup;
    @FXML private AnchorPane droughtPopup;
    @FXML private AnchorPane maxHarvestPopup;
    @FXML private AnchorPane maxWaterPopup;
    @FXML private Label locustPopupLabel = new Label("");
    @FXML private Label rainPopupLabel = new Label("");
    @FXML private Label droughtPopupLabel = new Label("");

    // inventory modal elements
    @FXML private AnchorPane inventoryModal;
    @FXML private Label onionCropCounter = new Label("0");
    @FXML private Label potatoCropCounter = new Label("0");
    @FXML private Label watermelonCropCounter = new Label("0");
    @FXML private Label cornCropCounter = new Label("0");

    // seed modal elements
    @FXML private AnchorPane seedModal;
    @FXML private Label watermelonSeedBagCounter = new Label("0");
    @FXML private Label potatoSeedBagCounter = new Label("0");
    @FXML private Label cornSeedBagCounter = new Label("0");
    @FXML private Label onionSeedBagCounter = new Label("0");

    //Tools
    @FXML
    private Label fertCounter = new Label("0");
    @FXML
    private Label pestCounter = new Label("0");

    // visibility toggles
    private BooleanProperty backgroundToggle = new SimpleBooleanProperty(false);
    private BooleanProperty seedModalToggle = new SimpleBooleanProperty(false);
    private BooleanProperty inventoryModalToggle = new SimpleBooleanProperty(false);
    private BooleanProperty locustPopupToggle = new SimpleBooleanProperty(false);
    private BooleanProperty rainPopupToggle = new SimpleBooleanProperty(false);
    private BooleanProperty rainAnimationToggle = new SimpleBooleanProperty(false);
    private BooleanProperty droughtPopupToggle = new SimpleBooleanProperty(false);
    private BooleanProperty maxHarvestPopupToggle = new SimpleBooleanProperty(false);
    private BooleanProperty maxWaterPopupToggle = new SimpleBooleanProperty(false);

    // seed image array for displaying starting seed
    private String[] seedImages = {
        "/main/resources/potatoes.png",
        "/main/resources/watermelon.png",
        "/main/resources/corn.png",
        "/main/resources/onion.png"};
    private boolean warning = false;
    private boolean usingTractor = false;
    private boolean usingIrrigation = false;




    ////////////////////////////////////////////////////
    ///////////  Initialize & InitData   ///////////////
    ////////////////////////////////////////////////////
    /**
     * This method runs before page load.
     * We use this to hide certain elements and to create arrays of FXML elements.
     *
     * @param url               The location used to resolve
     *                          relative paths for the root object,
     *                          or null if the location is not known.
     * @param resourceBundle    The resources used to
     *                          localize the root object,
     *                          or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        seedModal.visibleProperty().bind(seedModalToggle);
        background.visibleProperty().bind(backgroundToggle);
        inventoryModal.visibleProperty().bind(inventoryModalToggle);
        rainAnimation.visibleProperty().bind(rainAnimationToggle);

        locustPopup.visibleProperty().bind(locustPopupToggle);
        rainPopup.visibleProperty().bind(rainPopupToggle);
        droughtPopup.visibleProperty().bind(droughtPopupToggle);

        maxHarvestPopup.visibleProperty().bind(maxHarvestPopupToggle);
        maxWaterPopup.visibleProperty().bind(maxWaterPopupToggle);

        waterLevelsArray = new ImageView[] {
            plot1WaterLevel,
            plot2WaterLevel,
            plot3WaterLevel,
            plot4WaterLevel,
            plot5WaterLevel,
            plot6WaterLevel,
            plot7WaterLevel,
            plot8WaterLevel,
            plot9WaterLevel,
            plot10WaterLevel,
            plot11WaterLevel,
            plot12WaterLevel
        };

        fertLevelsArray = new ImageView[] {
            plot1FertLevel,
            plot2FertLevel,
            plot3FertLevel,
            plot4FertLevel,
            plot5FertLevel,
            plot6FertLevel,
            plot7FertLevel,
            plot8FertLevel,
            plot9FertLevel,
            plot10FertLevel,
            plot11FertLevel,
            plot12FertLevel
        };

        pesticideArray = new ImageView[] {
            plot1Pesticide,
            plot2Pesticide,
            plot3Pesticide,
            plot4Pesticide,
            plot5Pesticide,
            plot6Pesticide,
            plot7Pesticide,
            plot8Pesticide,
            plot9Pesticide,
            plot10Pesticide,
            plot11Pesticide,
            plot12Pesticide
        };
    }
    public static Game getGame() {
        return myGame;
    }


    /**
     * This method updates the UI based on current Game.java data.
     *
     * @param newGame   the Game object used to update UI with data
     */
    public void initData(Game newGame) {
        // establish new game and inventory
        myGame = newGame;
        if (plotPane != null) { // helper for testing purpose. keep it in future milestones
            inventory = myGame.getInventoryMap();

            // populate TOP LEFT data
            farmName.setText(myGame.getName());
            if (myGame.getGender().equals("FEMALE")) {
                avatar.setImage(new Image(getClass().
                        getResourceAsStream("/main/resources/female.png")));
            }
            backgroundSeason.setImage(
                    new Image(getClass().getResourceAsStream(setSeasonHelper())));

            currentSeed = myGame.getCurrentSeed();
            seedImage.setImage(
                    new Image(getClass()
                            .getResourceAsStream(setSeedHelper())));

            money.setText("$" + Integer.toString(myGame.getMoney()));

            // establish crop plots
            CropPlot[] myPlots = myGame.getPlots();
            for (int i = 0; i < myPlots.length; i++) {
                if (myPlots[i] != null && plotPane != null) {
                    ((ImageView) plotPane.getChildren().get(i * 2)).setImage(myPlots[i].getImage());
                    waterLevelsArray[i].setImage(myPlots[i].getWaterLevelImg());
                    fertLevelsArray[i].setImage(myPlots[i].getFertilizeImg());
                    if (myPlots[i].isPestApplied()) {
                        pesticideArray[i].setImage(new Image("/main/resources/Pesticide.png"));
                    } else {
                        pesticideArray[i].setImage(null);
                    }
                }
            }
            InventoryItem defaultItem = myGame.getDefaultItem();

            // Set seed counters to current inventory count.
            cornSeedBagCounter.setText(
                    Integer.toString(
                            inventory.get("Corn Seed").getCount()));
            watermelonSeedBagCounter.setText(
                    Integer.toString(
                            inventory.getOrDefault("Watermelon Seed", defaultItem).getCount()));
            onionSeedBagCounter.setText(
                    Integer.toString(
                            inventory.getOrDefault("Onion Seed", defaultItem).getCount()));
            potatoSeedBagCounter.setText(
                    Integer.toString(
                            inventory.getOrDefault("Potato Seed", defaultItem).getCount()));

            // Set crop counters to current inventory count.
            cornCropCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Corn", defaultItem).getCount()));
            watermelonCropCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Watermelon", defaultItem).getCount()));
            onionCropCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Onion", defaultItem).getCount()));
            potatoCropCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Potato", defaultItem).getCount()));
            pestCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Pesticide", defaultItem).getCount()));
            fertCounter
                    .setText(Integer.toString(inventory
                            .getOrDefault("Fertilizer", defaultItem).getCount()));
        }
        if (inventory.get("Tractor").getCount() > 0) {
            usingTractor = true;
            harvestToolImage.setImage(new Image(getClass()
                    .getResourceAsStream("/main/resources/tractor.png")));
            myGame.incrementMaxPlotHarvested();
        }
        if (inventory.get("Irrigation").getCount() > 0) {
            usingIrrigation = true;
            waterToolImage.setImage(new Image(getClass()
                    .getResourceAsStream("/main/resources/irrigation.png")));
            myGame.incrementMaxPlotWatered();
        }
    }

    ////////////////////////////////////////////////////
    ///////////////////  Methods   /////////////////////
    ////////////////////////////////////////////////////
    /**
     * Helper method to correctly display starting season background.
     *
     * @return  a String with the image URL for starting season
     */
    private String setSeasonHelper() {
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

    /**
     * Helper method to correctly display starting seed.
     *
     * @return  a String with the image URL for starting seed
     */
    private String setSeedHelper() {
        switch (currentSeed) {
        case ("Potato"):
            return seedImages[0];
        case ("Watermelon"):
            return seedImages[1];
        case ("Corn"):
            return seedImages[2];
        case ("Onion"):
            return seedImages[3];
        default:
            throw new IllegalStateException("Unexpected value: " + myGame.getCurrentSeed());
        }
    }

    /**
     * This method will toggle the background and seed modal on click of seed bag toolbar icon.
     *
     * @param actionEvent   location of mouse click
     */
    public void toggleSeedModal(ActionEvent actionEvent) {
        seedModalToggle.set(!seedModalToggle.get());
        backgroundToggle.set(!backgroundToggle.get());
    }

    /**
     * This method will toggle the background and inventory
     * modal on click of inventory (bot-left) icon.
     *
     * @param actionEvent   location of mouse click
     */
    public void toggleInventoryModal(ActionEvent actionEvent) {
        inventoryModalToggle.set(!inventoryModalToggle.get());
        backgroundToggle.set(!backgroundToggle.get());
    }

    //    /**
    //     * This method was used to close modals and background toggle on background click.
    //     * May implement later.
    //     */


    /**
     * This method will open the market scene on click of the market button.
     *
     * @param actionEvent   location of mouse click, used to get scene and change to market_ui
     * @throws IOException  not sure??
     */
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

    /**
     * This method will change the currently selected seed on seed bag click.
     *
     * @param actionEvent   location of mouseclick, unused in method
     */
    public void changeSeed(ActionEvent actionEvent) {
        myGame.setCurrentSeed(((Node) actionEvent.getSource()).getId());
        initData(myGame);
    }

    /**
     * This method set correct on click command, Seed, and changes cursor.
     * Will also remove on click command and reset cursor if already set to Seed.
     *
     * @param mouseEvent   mouse click that we use to set cursor
     */
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

    /**
     * This method set correct on click command, Harvest, and changes cursor.
     * Will also remove on click command and reset cursor if already set to Harvest.
     *
     * @param actionEvent   mouse click that we use to set cursor
     */
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

    /**
     * This method set correct on click command, Water, and changes cursor.
     * Will also remove on click command and reset cursor if already set to Water.
     *
     * @param actionEvent   mouse click that we use to set cursor
     */
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
     *
     * @param actionEvent Mouse Click event
     */
    public void plotClickedHandler(ActionEvent actionEvent) {
        String id = ((Node) actionEvent.getSource()).getId();
        String mode = myGame.getPlotClickMode();
        // Based on the current plotClickMode in Game.java the plots will behave in a certain way.
        if (mode != null) {
            switch (mode) {
            case "Harvest":
                harvestCrop(id);
                System.out.println("Crop harvested, " + id);
                break;
            case "Water":
                waterCrop(id);
                System.out.println("Plot watered, " + id);
                break;
            case "Seed":
                plantCrop(id);
                System.out.println("Seed planted, " + id);
                break;
            case "Fert":
                fertCrop(id);
                System.out.println("Crop fertilized, " + id);
                break;
            case "Pest":
                pestCrop(id);
                System.out.println("Pesticide sprayed, " + id);
                break;
            default:
                break;
            }
        }
    }

    private void pestCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        Map<String, InventoryItem> map = myGame.getInventoryMap();

        if (myPlots[plotId] != null) {
            if (!myPlots[plotId].isPestApplied()) {
                InventoryItem item = map.get("Pesticide");
                if (item.getCount() > 0) {
                    item.setCount(item.getCount() - 1);
                    myPlots[plotId].applyPesticide();
                }
            }
        }
        initData(myGame);
    }

    private void fertCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        CropPlot myCrop = myPlots[plotId];
        int count = myGame.getInventoryMap().get("Fertilizer").getCount();
        myGame.getInventoryMap().get("Fertilizer").setCount(myCrop.fertilizeCrop(count));
        this.initData(myGame);
    }

    /**
     * This method interacts with Game, Inventory, and UI to plant new crops.
     *
     * @param id    a String containing which plot was clicked
     */
    private void plantCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        Map<String, InventoryItem> map = myGame.getInventoryMap();

        if (myPlots[plotId] != null) {
            if (myPlots[plotId].getMaturity() == 0) {
                InventoryItem item = map.get(currentSeed + " Seed");
                if (item.getCount() > 0) {
                    item.setCount(item.getCount() - 5);
                    myPlots[plotId].setCropName(currentSeed);
                    myPlots[plotId].setMaturity(1);
                    myPlots[plotId].setWaterLevel(3);
                    myPlots[plotId].setFertilized(1);

                }
            }
        }
//        System.out.println(myPlots[plotId].getMaturity() + ", " + myPlots[plotId].getCropName());
//        System.out.println(map.get(currentSeed).getCount());
        initData(myGame);
    }

    /**
     * This method executes the necessary commands to harvest a crop.
     *
     * @param id    a String containing which plot was clicked
     */
    public void harvestCrop(String id) {
        if (myGame.getPlotHarvested() >= Game.MAX_HARVEST_PER_DAY) {
            maxHarvestPopupToggle.setValue(true);
            warning = true;
            return;
        }
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;
        InventoryItem defaultItem = myGame.getDefaultItem();
        Map<String, InventoryItem> map = myGame.getInventoryMap();
        CropPlot myCrop = myPlots[plotId];
//        System.out.println("Start of if statements.");
//        System.out.println(myCrop.getMaturity());
        // if plot is not empty and fully mature, we can harvest
        if (myCrop != null) {
            if (myCrop.getMaturity() == 3) {
                System.out.println("Crop is mature.");
                String cropName;
                if (!myCrop.isPestApplied()) {
                    cropName = myCrop.getCropName();
                } else {
                    cropName = myCrop.getCropName() + " P";
                }
                InventoryItem item = map.get(cropName);
                System.out.println(map.get(cropName).getCount());
                if (myCrop.getFertilized() == 0) {
                    item.setCount(item.getCount() + 5);
                } else if (myCrop.getFertilized() == 1) {
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                    item.setCount(item.getCount() + 5 + randomNum);
                } else if (myCrop.getFertilized() == 3 || myCrop.getFertilized() == 2) {
                    int randomNum = ThreadLocalRandom.current().nextInt(2, 3 + 1);
                    item.setCount(item.getCount() + 5 + randomNum);
                } else {
                    int randomNum = ThreadLocalRandom.current().nextInt(4, 5 + 1);
                    item.setCount(item.getCount() + 5 + randomNum);
                }

                if (item.getCount() > 50) {
                    item.setCount(50);
                }
                myPlots[plotId].resetCrop();
                myGame.incrementPlotHarvested();
            } else if (myPlots[plotId].getMaturity() == 5) {
                myPlots[plotId].resetCrop();
            }
        }
        this.initData(myGame);
    }

    public void waterCrop(String id) {
        if (myGame.getPlotWatered() > Game.MAX_WATER_PER_DAY) {
            myGame.resetPlotWatered();
            warning = true;
            return;
        }
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;
        CropPlot myCrop = myPlots[plotId];
        myCrop.waterCrop();
        myGame.incrementPlotWatered();
        this.initData(myGame);
    }
    public void nextDay(ActionEvent actionEvent) {
        // plant functions
        if (myGame.getMoney() == 0) {
            boolean end = true;
            CropPlot[] myPlots = myGame.getPlots();
            for (CropPlot myPlot : myPlots) {
                if (myPlot != null) {
                    if (myPlot.getMaturity() != 0 && myPlot.getMaturity() != 4) {
                        end = false;
                    }
                }
            }
            if (end) {
                try {
                    endGame(actionEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (CropPlot plot : myGame.getPlots()) {
            plot.nextDayCheck();
        }
        // change day in Game
        myGame.setDay(myGame.getDay() + 1);
        daysLabel.setText("Day " + myGame.getDay());
        // update UI
        Random rand = new Random();
        // set condition to rand.nextInt() % 10 > 0 during demo
        boolean testing = false;
        if (rand.nextInt() % 10 > 4 || testing) { // change back to || during demo
            warning = true;
            startRandomEvent();
        }

        // reset plotHarvested and plotWatered
        myGame.resetPlotHarvested();
        myGame.resetPlotWatered();
        this.initData(myGame);
    }

    private void endGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/endGame.FXML"));
        Parent endGame = loader.load();
        Scene endGameScreen = new Scene(endGame);

        EndGameController controller = loader.getController();
        controller.initData(myGame);
        // Stage and show the new scene
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Game Over");
        window.setScene(endGameScreen);
        window.show();
    }

    public void startRandomEvent() {
        Random rand = new Random();
        int eventNum = rand.nextInt(3);
        CropPlot[] gamePlot = myGame.getPlots();
        // hardcode eventnum to demo each event
//        eventNum = 2;
        switch (eventNum) {
        case 0: // rain
            System.out.println("rain");
            increaseWaterlevelRandomly(gamePlot);
            break;
        case 1: // drought
            System.out.println("drought");
            decreaseWaterlevelRandomly(gamePlot);
            break;
        case 2: // locust
            System.out.println("locust");
            killPlantsRandomly(gamePlot);
            break;
        default:
            break;
        }
    }

    public void increaseWaterlevelRandomly(CropPlot[] plot) {
        Random rand = new Random();
        rainPopupToggle.set(true);
        rainAnimationToggle.set(true);
        int increment = rand.nextInt(5);
        rainPopupLabel.setText(Integer.toString(increment));
        for (int i = 0; i < plot.length; i++) {
            if (!plot[i].getImage().getUrl().contains("/main/resources/blank.png")) {
                int newWaterLevel = plot[i].getWaterLevel() + increment;
                plot[i].setWaterLevel(newWaterLevel);
            }
        }
    }

    public void decreaseWaterlevelRandomly(CropPlot[] plot) {
        Random rand = new Random();
        droughtPopupToggle.set(true);
        int decrement = rand.nextInt(5);
        droughtPopupLabel.setText(Integer.toString(decrement));
        for (int i = 0; i < plot.length; i++) {
            if (!plot[i].getImage().getUrl().contains("/main/resources/blank.png")) {
                plot[i].setWaterLevel(plot[i].getWaterLevel() - decrement + 1);
            }
        }
    }

    public void killPlantsRandomly(CropPlot[] plot) {
        Random rand = new Random();
        locustPopupToggle.set(true);
        int count = 0;
        for (int i = 0; i < plot.length; i++) {
            boolean kill = rand.nextBoolean();
            if (kill && plot[i].getMaturity() > 0
                    && plot[i].getWaterLevel() > 0
                    && plot[i].getWaterLevel() < 4
                    && !plot[i].isPestApplied())  {
                plot[i].setMaturity(0);
                plot[i].setWaterLevel(0);
                count += 1;
            }
        }
        locustPopupLabel.setText(Integer.toString(count));
    }


    public void toolFertClick(ActionEvent actionEvent) {
        if (myGame.getPlotClickMode() == null || !(myGame.getPlotClickMode().equals("Fert"))) {
            myGame.setPlotClickMode("Fert");
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.MOVE);
        } else {
            myGame.setPlotClickMode(null);
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.DEFAULT);
        }
    }

    public void toolPestClick(ActionEvent actionEvent) {
        if (myGame.getPlotClickMode() == null || !(myGame.getPlotClickMode().equals("Pest"))) {
            myGame.setPlotClickMode("Pest");
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.OPEN_HAND);
        } else {
            myGame.setPlotClickMode(null);
            Scene myScene = ((Node) actionEvent.getSource()).getScene();
            myScene.setCursor(Cursor.DEFAULT);
        }

    }

    public void closeWarning(MouseEvent mouseEvent) {

        if (warning) {
            locustPopupToggle.set(false);
            droughtPopupToggle.set(false);
            rainPopupToggle.set(false);
            rainAnimationToggle.set(false);
            maxHarvestPopupToggle.set(false);
            maxWaterPopupToggle.set(false);
        }

        warning = false;
        initData(myGame);
    }
}
