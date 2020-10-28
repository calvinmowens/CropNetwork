package main.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.CropPlot;
import main.java.Game;
import main.java.Inventory;
import main.java.InventoryItem;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainUiController implements Initializable {

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


    private BooleanProperty seedBagClick = new SimpleBooleanProperty(false);
    private BooleanProperty inventoryClick = new SimpleBooleanProperty(false);
    private BooleanProperty backgroundToggle = new SimpleBooleanProperty(false);

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
        seedModal.visibleProperty().bind(seedBagClick);
        background.visibleProperty().bind(backgroundToggle);
//        inventoryModal.visibleProperty().bind(inventoryClick);
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

        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));

        money.setText("$" + Integer.toString(myGame.getMoney()));
        Map<String, InventoryItem> inventory = myGame.getInventoryMap();

        CropPlot[] myPlots = myGame.getPlots();
        for (int i = 0; i < myPlots.length; i++) {
            if (myPlots[i] != null) {
                ((ImageView) plotPane.getChildren().get(i * 2)).setImage(myPlots[i].getImage());
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
        String[] seedImages = {"/main/resources/potatoes.png",
            "/main/resources/watermelon.png", "/main/resources/corn.png",
            "/main/resources/onion.png"};
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
    }

    public void toggleSeedModal(ActionEvent actionEvent) {
        seedBagClick.set(!seedBagClick.get());
        backgroundToggle.set(!backgroundToggle.get());
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

    public void openInventoryModal(ActionEvent actionEvent) {
        inventoryClick.set(!inventoryClick.get());
        backgroundToggle.set(!backgroundToggle.get());
    }

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
        String id = ((Node) actionEvent.getSource()).getId();
        String mode = myGame.getPlotClickMode();
        if (mode.equals("Harvest")) {
            harvestCrop(id);
        }
    }

    public void harvestCrop(String id) {
        CropPlot[] myPlots = myGame.getPlots();
        int plotId = Integer.parseInt(id.substring(4)) - 1;

        InventoryItem defaultItem = myGame.getDefaultItem();
        Map<String, InventoryItem> map = myGame.getInventoryMap();
        CropPlot myCrop = myPlots[plotId];
        if (myPlots[plotId] != null && myPlots[plotId].getMaturity() == 3) {
            String cropName = myCrop.getCropName().substring(0,myCrop.getCropName().length() - 7);
            InventoryItem item = map.get(cropName);
            item.setCount(item.getCount() + 5);
        }
        myPlots[plotId].setCropName("Empty Plot");
        myPlots[plotId].setMaturity(0);
        myPlots[plotId].setImgUrl("/main/resources/blank.png");
        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));
        this.initData(myGame);
    }
}
