package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.stage.Stage;
import main.java.CropPlot;
import main.java.Game;
import main.java.Inventory;
import main.java.InventoryItem;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class GameConfigController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ChoiceBox<String> difficulty;
    @FXML
    private ChoiceBox<String> startingSeed;
    @FXML
    private ChoiceBox<String> startingSeason;

    private Game newGame;

    public Game getGame() {
        return this.newGame;
    }

    public void setName(String name) {
        this.name = new TextField(name);
    }

    public TextField getName() {
        return this.name;
    }

    public void setGender(String gender) {
        this.gender = new ToggleGroup();
        RadioButton male = new RadioButton("MALE");
        male.setToggleGroup(this.gender);
        male.setUserData("M");

        RadioButton female = new RadioButton("FEMALE");
        female.setToggleGroup(this.gender);
        female.setUserData("F");
        ObservableList<Toggle> m = this.gender.getToggles();
        this.gender.selectToggle(this.gender.getToggles().get(gender == "MALE" ? 0 : 1));
    }
    public ToggleGroup getGender() {
        return this.gender;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = new ChoiceBox<>();
        this.difficulty.setValue(difficulty);
    }

    public ChoiceBox<String> getDifficulty() {
        return this.difficulty;
    }

    public void setStartingSeed(String seed) {
        this.startingSeed = new ChoiceBox<>();
        this.startingSeed.setValue(seed);
    }

    public ChoiceBox<String> getStartingSeed() {
        return this.startingSeed;
    }

    public void setStartingSeason(String season) {
        this.startingSeason = new ChoiceBox<>();
        this.startingSeason.setValue(season);
    }

    public ChoiceBox<String> getStartingSeason() {
        return this.startingSeason;
    }

    /**
     * This method initializes out choice boxes with the selection items we want
     * them to have.
     * @param url The location used to resolve
     *            relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to
     *                       localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficulty.getItems().add("Casual");
        difficulty.getItems().add("Regular");
        difficulty.getItems().add("Hardcore");

        startingSeed.getItems().add("Corn");
        startingSeed.getItems().add("Onion");
        startingSeed.getItems().add("Watermelon");
        startingSeed.getItems().add("Potato");

        startingSeason.getItems().add("Spring");
        startingSeason.getItems().add("Summer");
        startingSeason.getItems().add("Fall");
        startingSeason.getItems().add("Winter");
    }

    public void startGame(ActionEvent actionEvent) throws IOException {
        if (name.getText() != null && !name.getText().isBlank() && difficulty.getValue() != null
                && startingSeed.getValue() != null && startingSeason.getValue() != null
                && gender.getSelectedToggle() != null) {
            // Creates a new Game object with information from the Screen.
            newGame = new Game();

            newGame.setName(name.getText());
            newGame.setGender(((RadioButton) gender.getSelectedToggle()).getText());
            newGame.setDifficulty(difficulty.getValue());
            newGame.setCurrentSeed(startingSeed.getValue());
            newGame.setStartingSeason(startingSeason.getValue());
            newGame.setInventory(new Inventory());
            newGame.setMoney(newGame.getDifficulty());
            newGame.initializeInventory();
            for (int i = 0; i < newGame.getPlots().length; i++) {
                Random rand = new Random();
                int cropNum = rand.nextInt(4) + 1;
                int maturityNum = rand.nextInt(3) + 1;
                if (cropNum == 1) {
                    newGame.getPlots()[i] = new CropPlot("Corn", maturityNum);
                } else if (cropNum == 2) {
                    newGame.getPlots()[i] = new CropPlot("Watermelon", maturityNum);
                } else if (cropNum == 3) {
                    newGame.getPlots()[i] = new CropPlot("Onion", maturityNum);
                } else if (cropNum == 4) {
                    newGame.getPlots()[i] = new CropPlot("Potato", maturityNum);
                }
                if(i == 11 || i == 10) {
                    newGame.getPlots()[i] = new CropPlot("Empty Plot", 0, 0, 0);
                }
            }
            newGame.getPlots()[0] = new CropPlot("Onion", 3);

            Map<String, InventoryItem> myMap = newGame.getInventoryMap();
            //myMap.put("default", new InventoryItem(0, "default",
            // "/main/resources/blank.png", 0)); // used for getOrDefault()
            switch (startingSeed.getValue()) {
            case "Corn":
                myMap.put("Corn Seed",
                        new InventoryItem(10, "Corn Seed",
                                "/main/resources/cornBag.png", 10));
                break;
            case "Onion":
                myMap.put("Onion Seed",
                        new InventoryItem(10, "Onion Seed",
                                "/main/resources/onionBag.png", 10));
                break;
            case "Potato":
                myMap.put("Potato Seed",
                        new InventoryItem(10, "Potato Seed",
                                "/main/resources/potatoBag.png", 10));
                break;
            case "Watermelon":
                myMap.put("Watermelon Seed",
                        new InventoryItem(10, "Watermelon Seed",
                                "/main/resources/watermelonBag.png", 10));
                break;
            default:
                break;
            }

            // setting up the loader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/screens/main_ui.FXML"));
            Parent startGame = loader.load();
            Scene startGameScene = new Scene(startGame);
            MainUiController controller = loader.getController();
            controller.initData(newGame);
            // Stage and show the new scene
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Main UI");
            window.setScene(startGameScene);
            window.show();
        }
    }
}

