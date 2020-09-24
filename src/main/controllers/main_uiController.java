package main.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class main_uiController implements Initializable {

    private static Game myGame;
    public AnchorPane background;
    public ImageView background_season;
    public AnchorPane seed_modal;

    private BooleanProperty seedBagClick = new SimpleBooleanProperty(false);

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
        seed_modal.visibleProperty().bind(seedBagClick);
        background.visibleProperty().bind(seedBagClick);
    }

    /**
     * This method initializes the Game UI with settings from the prev scene
     * TODO: Parth implement this
     * @param game  param to get information from prev scene
     */
    public void initData(Game game) {
        myGame = game;
        String[] seasonImages = {"spring.png", "summer.png", "fall.png", "winter.png"};

    }


    public void toggleSeedModal(ActionEvent actionEvent) {
        seedBagClick.set(!seedBagClick.get());
    }
}
