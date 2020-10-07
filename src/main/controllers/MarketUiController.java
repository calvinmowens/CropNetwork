package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketUiController implements Initializable {

    private Game myGame;

    public Label currentPlayerMoney;
    public ImageView selectedItemImage;
    public Label selectedItemName;
    public Label selectedItemPrice;
    public TextField selectedItemQuantity;
    public Label selectedItemTotal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;
        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));
        // TODO update with inventory and store items
    }

    public void switchToInventory(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/marketInventory_ui.FXML"));
        Parent marketInventory = loader.load();
        Scene startMarketInventory = new Scene(marketInventory);

        MarketInventoryUiController controller = loader.getController();
        controller.initData(myGame);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Sell");
        window.setScene(startMarketInventory);
        window.show();
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
    }

    public void buyItem(ActionEvent actionEvent) {
    }

    public void exitMarket(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/main_ui.FXML"));
        Parent exitMarket = loader.load();
        Scene startExitMarket = new Scene(exitMarket);

        MainUiController controller = loader.getController();
        controller.initData(myGame);
        // Stage and show the new scene
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setTitle("Welcome, " + myGame.getName());
        window.setScene(startExitMarket);
        window.show();
    }
}
