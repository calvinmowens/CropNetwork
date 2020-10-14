package main.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import main.java.Market;
import org.w3c.dom.Text;

import javax.swing.*;
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
    @FXML
    private AnchorPane marketItem1;
    @FXML
    private AnchorPane marketItem2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;
        Market myMarket = myGame.getMarket();

        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));

        /** Add corn to marketplace */
        ImageView item1Image = (ImageView) (marketItem1.getChildren().get(0));
        Label item1Label = (Label) (marketItem1.getChildren().get(1));
        item1Image.setImage(myMarket.getCornSeed().getImage());
        item1Label.setText("$"+myGame.getSeedPrice());

        /** Add watermelon to marketplace */
        ImageView item2Image = (ImageView) (marketItem2.getChildren().get(0));
        Label item2Label = (Label) (marketItem2.getChildren().get(1));
        item2Image.setImage(myMarket.getWatermelonSeed().getImage());
        item2Label.setText("$"+myGame.getSeedPrice());

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
        String id = ((Node) mouseEvent.getSource()).getId();
        int slotId = Integer.parseInt(id.substring(10)) - 1;
        if (slotId == 0) {
            selectedItemName.setText("Corn");
            selectedItemImage.setImage(new Image("/main/resources/corn.png"));
        } else if (slotId == 1){
            selectedItemName.setText("Watermelon");
            selectedItemImage.setImage(new Image("/main/resources/watermelon.png"));
        }
        selectedItemPrice.setText(Integer.toString(myGame.getCropPrice()));
    }

    public void buyItem(ActionEvent actionEvent) {
        int buyAmount = Integer.parseInt(selectedItemQuantity.getText());
        String selectedItem = selectedItemName.getText();
        System.out.println(selectedItem);
        myGame.buyFromMarket(selectedItem, buyAmount, myGame.getSeedPrice());
        this.initData(myGame);
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
