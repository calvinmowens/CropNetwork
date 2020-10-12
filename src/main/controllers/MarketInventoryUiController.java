package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Game;
import main.java.InventoryItem;
import main.java.Item;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketInventoryUiController implements Initializable {

    private Game myGame;

    public Label currentPlayerMoney;
    public ImageView selectedItemImage;
    public Label selectedItemName;
    public Label selectedItemPrice;
    public TextField selectedItemQuantity;
    public Label selectedItemTotal;
    @FXML
    private AnchorPane inventoryItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;
        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));
        System.out.println(myGame.getInventory().toString());
        System.out.println( myGame.getInventoryList().get(0).getItemName());
        List<InventoryItem> myInventory = myGame.getInventoryList();
        for (int i = 0; i < myInventory.size(); i++) {
            AnchorPane itemSlot = (AnchorPane)(inventoryItems.getChildren().get(0));
            ImageView itemImg = (ImageView) itemSlot.getChildren().get(0);
            itemImg.setImage(myInventory.get(i).getImage());
            Label itemCount = (Label) itemSlot.getChildren().get(1);
            itemCount.setText(myInventory.get(i).getCount() + "/ 100");
        }
        // TODO update with inventory and store items

    }

    public void switchToMarket(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/market_ui.FXML"));
        Parent market = loader.load();
        Scene startMarket = new Scene(market);

        MarketUiController controller = loader.getController();
        controller.initData(myGame);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Buy");
        window.setScene(startMarket);
        window.show();
    }

    public void sellItem(ActionEvent actionEvent) {
        int sellAmount = Integer.parseInt(selectedItemQuantity.getText());
        String selectedItem = selectedItemName.getText();
//        int price = Integer.parseInt(selectedItemPrice.getText());
        myGame.sellFromInventory(selectedItem, sellAmount, myGame.getCropPrice());
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
    }

    public void exitMarket(MouseEvent mouseEvent) throws IOException{
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
