package main.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketUiController {
    public void switchToInventory(MouseEvent mouseEvent) throws IOException {
        Parent marketView = FXMLLoader.load(getClass().getResource("/main/screens/marketInventory_ui.FXML"));
        Scene newPageScene = new Scene(marketView);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(newPageScene);
        window.show();
    }
}
