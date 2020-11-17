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
import java.util.*;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

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

    private Map<String, InventoryItem> map;
    private Set<String> keys;
    private String[] keyArray = new String[12];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;

        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));

        //List<InventoryItem> myInventory = myGame.getInventoryList();
        map = myGame.getInventoryMap();
        keys = map.keySet();
        String[] tempKeyArray = keys.toArray(new String[keys.size()]);
        int count = 0;
        System.out.println(Arrays.toString(tempKeyArray));
        for(int i = 0; i < tempKeyArray.length; i++) {
            if(i != 8 && i != 13) {
                keyArray[count] = tempKeyArray[i];
                count++;
            }
        }
        System.out.println(Arrays.toString(keyArray));
        for (int i = 0; i < keyArray.length; i++) {
            //THIS IS A TEMPORARY FIX, WE MUST ADD SPACE IN THE MARKET AND/OR REORDER OUR ELEMENTS
            String name = keyArray[i];
            AnchorPane itemSlot = (AnchorPane) (inventoryItems.getChildren().get(i));
            ImageView itemImg = (ImageView) itemSlot.getChildren().get(0);
            itemImg.setImage(map.get(keyArray[i]).getImage());
            Label itemCount = (Label) itemSlot.getChildren().get(1);
            itemCount.setText(map.get(keyArray[i]).getCount() + "/ 100");
            count++;
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
        for (InventoryItem i: map.values()) {
            if (i.getItemName().equals(selectedItemName.getText()) && i.getCount() >= sellAmount) {
                String selectedItem = selectedItemName.getText();
//                i.setCount(i.getCount() - sellAmount);
                myGame.sellFromInventory(selectedItem, sellAmount, i.getBasePrice());
            }
        }
        this.initData(myGame);
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
        String id = ((Node) mouseEvent.getSource()).getId();
        int slotId = Integer.parseInt(id.substring(13)) - 1;
        System.out.println();
        selectedItemName.setText(map.get(keyArray[slotId]).getItemName());
        selectedItemPrice.setText(Integer.toString(map.get(keyArray[slotId]).getBasePrice()));
        selectedItemImage.setImage(map.get(keyArray[slotId]).getImage());
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
