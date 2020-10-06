package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketInventoryUiController {

    public void switchToMarket(MouseEvent mouseEvent) throws IOException {
        Parent marketView = FXMLLoader.load(getClass().getResource("/main/screens/market_ui.FXML"));
        Scene newPageScene = new Scene(marketView);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(newPageScene);
        window.show();
    }

    public void sellItem(ActionEvent actionEvent) {
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
    }
}
