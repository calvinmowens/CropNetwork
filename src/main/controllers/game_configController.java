package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

public class game_configController {

    public TextField name;
    public ToggleGroup gender;
    public ChoiceBox difficulty;
    public ChoiceBox startingSeed;
    public ChoiceBox startingSeason;

    public void startGame(ActionEvent actionEvent) throws IOException {

        // Creates a new Game object with information from the Screen.
        Game newGame = new Game();
        newGame.setName(name.getText());
        newGame.setGender(((RadioButton)gender.getSelectedToggle()).getText());
        newGame.setDifficulty();

        // setting up the loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main_ui.FXML"));
        Parent startGame = loader.load();
        Scene startGameScene = new Scene(startGame);

        main_uiController controller = loader.getController();
        controller.initData(newGame);

        // Stage and show the new scene
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(startGameScene);
        window.show();
    }

}
