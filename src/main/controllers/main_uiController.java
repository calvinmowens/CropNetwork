package main.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class main_uiController implements Initializable {

    public ImageView seedImage;
    public ImageView avatar;
    private Game myGame;
    public AnchorPane background;
    public ImageView background_season;
    public AnchorPane seed_modal;
    public Label money;
    public Label farm_name;

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
     * @param newGame  param to get information from prev scene
     */
    public void initData(Game newGame) {
        myGame = newGame;

        farm_name.setText(myGame.getName());

        if (myGame.getGender().equals("FEMALE")) {
            avatar.setImage(new Image(getClass().getResourceAsStream("/main/resources/female.png")));
        }

        background_season.setImage(new Image(getClass().getResourceAsStream(setStartingSeasonHelper())));

        seedImage.setImage(new Image(getClass().getResourceAsStream(setStartingSeedHelper())));

        myGame.setMoney(myGame.getDifficulty());
        money.setText("$" + Integer.toString(myGame.getMoney()));

        System.out.println(myGame.toString());
    }

    private String setStartingSeasonHelper() {
        String[] seasonImages = {"/main/resources/spring.png", "/main/resources/summer.png", "/main/resources/fall.png", "/main/resources/winter.png"};
        switch(myGame.getStartingSeason()) {
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
        String[] seedImages = {"/main/resources/potatoes.png", "/main/resources/watermelon.png", "/main/resources/corn.png", "/main/resources/onion.png"};
        switch(myGame.getStartingSeed()) {
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
    }
}
