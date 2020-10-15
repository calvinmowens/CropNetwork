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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Game;
import main.java.InventoryItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketInventoryUiController implements Initializable {

    @FXML
    private AnchorPane inventoryItems;
    @FXML
    private Game myGame;
    @FXML
    private Label currentPlayerMoney;
    @FXML
    private ImageView selectedItemImage;
    @FXML
    private Label selectedItemName;
    @FXML
    private Label selectedItemPrice;
    @FXML
    private TextField selectedItemQuantity;
    @FXML
    private Label selectedItemTotal;

    @FXML
    private AnchorPane inventoryItem1;
    @FXML
    private AnchorPane inventoryItem2;
    @FXML
    private AnchorPane inventoryItem3;
    @FXML
    private AnchorPane inventoryItem4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;

        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));

        List<InventoryItem> myInventory = myGame.getInventoryList();

        for (int i = 0; i < myInventory.size(); i++) {
            AnchorPane itemSlot = (AnchorPane) (inventoryItems.getChildren().get(i));
            ImageView itemImg = (ImageView) itemSlot.getChildren().get(0);
            itemImg.setImage(myInventory.get(i).getImage());
            Label itemCount = (Label) itemSlot.getChildren().get(1);
            itemCount.setText(myInventory.get(i).getCount() + "/ 100");
        }


    }

    public void switchToMarket(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/market_ui.FXML"));
        Parent market = loader.load();
        Scene startMarket = new Scene(market);

        MarketUiController controller = loader.getController();
        controller.initData(myGame);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Buy");
        window.setScene(startMarket);
        window.show();
    }

    public void sellItem(ActionEvent actionEvent) {
        int sellAmount = Integer.parseInt(selectedItemQuantity.getText());
        for (InventoryItem i: myGame.getInventoryList()) {
            if (i.getItemName() == selectedItemName.getText() && i.getCount() >= sellAmount) {
                String selectedItem = selectedItemName.getText();
                i.setCount(i.getCount() - sellAmount);
                myGame.sellFromInventory(selectedItem, sellAmount, myGame.getCropPrice());
            }
        }
        this.initData(myGame);
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
        String id = ((Node) mouseEvent.getSource()).getId();
        int slotId = Integer.parseInt(id.substring(13)) - 1;
        selectedItemName.setText(myGame.getInventoryList().get(slotId).getItemName());
        selectedItemImage.setImage(myGame.getInventoryList().get(slotId).getImage());
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
