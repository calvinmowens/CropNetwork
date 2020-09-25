package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.java.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class game_configController implements Initializable {

    public TextField name;
    public ToggleGroup gender;
    public ChoiceBox<String> difficulty;
    public ChoiceBox<String> startingSeed;
    public ChoiceBox<String> startingSeason;

    /**
     * This method initializes out choice boxes with the selection items we want
     * them to have.
     * @param url
     * @param resourceBundle
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
        if (name.getText() != null && !name.getText().isBlank() && difficulty.getValue() != null && startingSeed.getValue()!= null && startingSeason.getValue() != null && gender.getSelectedToggle() != null) {
            // Creates a new Game object with information from the Screen.
            Game newGame = new Game();
            newGame.setName(name.getText());
            newGame.setGender(((RadioButton) gender.getSelectedToggle()).getText());
            newGame.setDifficulty(difficulty.getValue());
            newGame.setStartingSeed(startingSeed.getValue());
            newGame.setStartingSeason(startingSeason.getValue());

            // setting up the loader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main/screens/main_ui.FXML"));
            Parent startGame = loader.load();
            Scene startGameScene = new Scene(startGame);

            main_uiController controller = loader.getController();
            controller.initData(newGame);
            // Stage and show the new scene
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(startGameScene);
            window.show();
        }
    }
}
