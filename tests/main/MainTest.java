package main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.Game;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;


public class MainTest extends ApplicationTest {
    @FXML
    private ImageView plot9Image;
    private Pane mainroot;
    private Stage mainstage;
    private Object controller;
    private Game myGame;

    @Override
    public void start(Stage stage) throws IOException {
        mainroot = FXMLLoader.load(Main.class.getResource("screens/welcome_screen.fxml"));
        mainstage = stage;
        mainstage.setTitle("Welcome Screen");
        mainstage.setScene(new Scene(mainroot, 1280, 720));
        mainstage.show();
    }



    @Test
    public void changePlayerMoneyOnPurchase() {
        before();
        moveTo("#market").clickOn("#market");
        moveTo("#marketItem4").clickOn("#marketItem4");
        moveTo("#selectedItemQuantity").clickOn("#selectedItemQuantity").write("5");
        moveTo("#buyItemBtn").clickOn("#buyItemBtn");
        verifyThat("#currentPlayerMoney", LabeledMatchers.hasText("9650"));
    }

    @Test
    public void changePlayerMoneyOnSale() {
        before();
        moveTo("#plot9").clickOn("#plot9");
        moveTo("#market").clickOn("#market");
        moveTo("#inventory").clickOn("#inventory");
        moveTo("#inventoryItem1").clickOn("#inventoryItem1");
        moveTo("#selectedItemQuantity").clickOn("#selectedItemQuantity").write("3");
        moveTo("#sellItemBtn").clickOn("#sellItemBtn");
        verifyThat("#currentPlayerMoney", LabeledMatchers.hasText("10390"));

    }

    @Test
    public void preventPurchaseWhenMoneyNotEnough() {
        beforeHardcore();
        moveTo("#market").clickOn("#market");
        moveTo("#marketItem4").clickOn("#marketItem4");
        moveTo("#selectedItemQuantity").clickOn("#selectedItemQuantity").write("10");
        moveTo("#buyItemBtn").clickOn("#buyItemBtn");
        verifyThat("#currentPlayerMoney", LabeledMatchers.hasText("1000"));
    }

    @Test
    public void preventExcessPurchase() {
        before();
        moveTo("#market").clickOn("#market");
        moveTo("#marketItem4").clickOn("#marketItem4");
        moveTo("#selectedItemQuantity").clickOn("#selectedItemQuantity").write("101");
        moveTo("#buyItemBtn").clickOn("#buyItemBtn");
        verifyThat("#currentPlayerMoney", LabeledMatchers.hasText("10000"));
    }

    @Test
    public void plotClearedAfterHarvest() {
        before();
        ImageView plot9 = ((ImageView) ((AnchorPane)
                (mainstage.getScene().getRoot().getChildrenUnmodifiable().get(11)))
                .getChildren().get(16));
        moveTo("#plot9").clickOn("#plot9");
        Image blank = new Image("/main/resources/blank.png");
        Assert.assertEquals(blank.getUrl(), plot9.getImage().getUrl());
    }

    public void before() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("1");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
    }
    public void beforeHardcore() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("1");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Hardcore");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
    }

    @Test
    public void marketDisplayCorrectly() {
        before();
        moveTo("#market").clickOn("#market");
        verifyThat(window("Market: Buy"), WindowMatchers.isShowing());
    }
    @Test
    public void marketInventoryDisplayCorrectly() {
        before();
        moveTo("#market").clickOn("#market");
        moveTo("#inventory").clickOn("#inventory");
        verifyThat(window("Market: Sell"), WindowMatchers.isShowing());
    }

}