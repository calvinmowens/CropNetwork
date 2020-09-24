package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class game_configController {

    public void startGame(ActionEvent actionEvent) throws IOException {

        /**
         * TODO:
         * Scrape this page of contents and save to Game.java before continuing.
         * Pass to main_ui controller.
         */

        Parent startGame = FXMLLoader.load(getClass().getResource("../screens/main_ui.FXML"));
        Scene startGameScene = new Scene(startGame);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(startGameScene);
        window.show();
    }

}
