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
import main.java.Game;
import main.java.Market;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUiController implements Initializable {

    public AnchorPane inventoryModal;
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
        inventoryModal.visibleProperty().bind(inventoryClick);

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

        myGame.setMoney(myGame.getDifficulty());
        money.setText("$" + Integer.toString(myGame.getMoney()));

        switch (myGame.getStartingSeed()) {
            case "Onion":
                onionSeedBagCounter.setText("10");
                myGame.getInventory().setOnionSeedCount(10);
                break;
            case "Corn":
                cornSeedBagCounter.setText("10");
                myGame.getInventory().setCornSeedCount(10);
                break;
            case "Watermelon":
                watermelonSeedBagCounter.setText("10");
                myGame.getInventory().setWatermelonSeedCount(10);
                break;
            case "Potato":
                potatoSeedBagCounter.setText("10");
                myGame.getInventory().setPotatoSeedCount(10);
                break;
        }
        System.out.println(myGame.toString());
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
        myGame.setMarket(new Market());
        controller.initData(myGame);
        // Stage and show the new scene
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Buy");
        window.setScene(startOpenMarket);
        window.show();
    }

    public void openInventoryModal(ActionEvent actionEvent) {
        System.out.println("hello");
        inventoryClick.set(!inventoryClick.get());
        backgroundToggle.set(!backgroundToggle.get());

    }
}
