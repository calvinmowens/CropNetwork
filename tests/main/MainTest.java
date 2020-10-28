package main;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.Game;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {
    private Button button;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(Main.class.getResource("screens/welcome_screen.fxml"));
        stage.setTitle("Welcome Screen");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();

    }

    //Test By Sungeun Bae
    @Test
    public void whenStartGamePressedGameConfigWindowOpens() {
        // when:
        clickOn("#startGameBtn");

        // then:
        verifyThat(window("Config Screen"), WindowMatchers.isShowing());
    }

    //Test By Sungeun Bae
    @Test
    public void whenSeeCreditPressedNothingOpens() {
        // when:
        clickOn("#seeCreditBtn");

        // then:
        verifyThat(window("Welcome Screen"), WindowMatchers.isShowing());
    }



    // Test by Selena Do
    @Test
    public void initialDaySetToDay1() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("Selenium Doremi");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat("#days", LabeledMatchers.hasText("Day 1"));
    }

    // Test by Selena Do
    @Test
    public void display10000DollarWhenDifficultyIsCasual() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("Selenium Doremi");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat("#money", LabeledMatchers.hasText("$10000"));
    }

    // Test by Parth Shah
    @Test
    public void farmNameSetToPlayerName() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("Part Saw");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat("#farmName", LabeledMatchers.hasText("Part Saw"));
    }

    //Test By Parth Shah
    @Test
    public void difficultyChangesStartingMoney() {
        Game game = new Game();
        String[] diffArray = new String[]{"Casual", "Regular", "Hardcore"};
        int[] moneyArray = new int[]{10000, 5000, 1000};
        for (int i = 0; i < 3; i++) {
            game.setDifficulty(diffArray[i]);
            game.setMoney(game.getDifficulty());
            assertEquals(moneyArray[i], game.getMoney());
        }
    }

    // Test by Luke Wooseok Kim
    @Test
    public void display5000DollarWhenDifficultyIsRegular() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("Lewk Khem");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Regular");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat("#money", LabeledMatchers.hasText("$5000"));
    }

    // Test by Luke Wooseok Kim
    @Test
    public void displayConfigScreenWhenNameIsBlankText() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("     ");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat(window("Config Screen"), WindowMatchers.isShowing());
    }

    // Test by Calvin Owens
    @Test
    public void displayConfigScreenWhenNameIsEmptyString() {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat(window("Config Screen"), WindowMatchers.isShowing());
    }

    // Test by Calvin Owens
    @Test
    public void displayMainUIWhenFieldsMeetCriteria() throws IOException {
        clickOn("#startGameBtn");
        moveTo("#name").clickOn("#name").write("Kelvin Birmingham Owens");
        moveTo("#difficulty").clickOn("#difficulty").clickOn("Casual");
        moveTo("#FemaleRadioBtn").clickOn("#FemaleRadioBtn");
        moveTo("#startingSeed").clickOn("#startingSeed").clickOn("Onion");
        moveTo("#startingSeason").clickOn("#startingSeason").clickOn("Spring");
        moveTo("#startGameBtn").clickOn("#startGameBtn");
        verifyThat(window("Main UI"), WindowMatchers.isShowing());
    }
}