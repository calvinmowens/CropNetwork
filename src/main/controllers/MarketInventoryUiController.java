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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketInventoryUiController implements Initializable {

    public AnchorPane inventoryItems;
    private Game myGame;

    public Label currentPlayerMoney;
    public ImageView selectedItemImage;
    public Label selectedItemName;
    public Label selectedItemPrice;
    public TextField selectedItemQuantity;
    public Label selectedItemTotal;

    @FXML
    public AnchorPane inventoryItem1;
    @FXML
    public AnchorPane inventoryItem2;
    @FXML
    public AnchorPane inventoryItem3;
    @FXML
    public AnchorPane inventoryItem4;

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
            System.out.println(i+", "+myInventory.get(i).getItemName());
            AnchorPane itemSlot = (AnchorPane)(inventoryItems.getChildren().get(i));
            ImageView itemImg = (ImageView) itemSlot.getChildren().get(0);
            itemImg.setImage(myInventory.get(i).getImage());
            Label itemCount = (Label) itemSlot.getChildren().get(1);
            itemCount.setText(myInventory.get(i).getCount() + "/ 100");
        }
//        myGame = currentGame;
//        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));
//
//        if (myGame.getInventory().getCornCount() != 0) {
//            ImageView item1Image = (ImageView) (inventoryItem1.getChildren().get(0));
//            Label item1Label = (Label) (inventoryItem1.getChildren().get(1));
//            item1Image.setImage(new Image("/main/resources/corn_mature.png"));
//            item1Label.setText("$"+myGame.getCropPrice());
//        }
//
//        if (myGame.getInventory().getWatermelonCount() != 0) {
//            ImageView item2Image = (ImageView) (inventoryItem2.getChildren().get(0));
//            Label item2Label = (Label) (inventoryItem2.getChildren().get(1));
//            item2Image.setImage(new Image("/main/resources/WatermelonCrop.png"));
//            item2Label.setText("$"+myGame.getCropPrice());
//        }
//
//        if (myGame.getInventory().getOnionCount() != 0) {
//            ImageView item3Image = (ImageView) (inventoryItem3.getChildren().get(0));
//            Label item3Label = (Label) (inventoryItem3.getChildren().get(1));
//            item3Image.setImage(new Image("/main/resources/OnionCrop.png"));
//            item3Label.setText("$"+myGame.getCropPrice());
//        }
//
//        if (myGame.getInventory().getPotatoCount() != 0) {
//            ImageView item4Image = (ImageView) (inventoryItem4.getChildren().get(0));
//            Label item4Label = (Label) (inventoryItem4.getChildren().get(1));
//            item4Image.setImage(new Image("/main/resources/PotatoCrop.png"));
//            item4Label.setText("$"+myGame.getCropPrice());
//        }

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
        String id = ((Node) mouseEvent.getSource()).getId();
        int slotId = Integer.parseInt(id.substring(13)) - 1;
        selectedItemName.setText(myGame.getInventoryList().get(slotId).getItemName());
        selectedItemImage.setImage(myGame.getInventoryList().get(slotId).getImage());
        selectedItemPrice.setText(Integer.toString(myGame.getCropPrice()));
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
