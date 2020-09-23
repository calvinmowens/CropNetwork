package welcome_screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class welcomeController {

    public void changeScreen(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game_config.fxml"));
        Main.getGameStage().setScene(new Scene(root, 1280, 720));

    }
}
