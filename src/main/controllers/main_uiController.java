package main.controllers;

import javafx.fxml.Initializable;
import main.java.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class main_uiController implements Initializable {

    private static Game myGame;

    /**
     * TODO:
     * Create clock method and update time on 10ms basis. Best way to do this?
     * Update elements based on information from Game.java
     */

    /**
     * This method is used to hide certain scene elements prior to page load.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method initializes the Game UI with settings from the prev scene
     * @param game  param to get information from prev scene
     */
    public void initData(Game game) {
        myGame = game;
        // name
        // season
        // money - based on difficulty
        // gender
    }


}
