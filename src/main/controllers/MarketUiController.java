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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketUiController implements Initializable {

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
    private AnchorPane marketItem1;
    @FXML
    private AnchorPane marketItem2;
    @FXML
    private AnchorPane marketItem3;
    @FXML
    private AnchorPane marketItem4;
    @FXML
    private AnchorPane marketItem5;
    @FXML
    private AnchorPane marketItem6;
    @FXML
    private AnchorPane marketItem7;
    @FXML
    private AnchorPane marketItem8;
    @FXML
    private AnchorPane marketItem9;
    @FXML
    private AnchorPane marketItem10;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedItemImage.setImage(null);
        selectedItemName.setText(null);
        selectedItemPrice.setText(null);
    }

    public void initData(Game currentGame) {
        myGame = currentGame;

        currentPlayerMoney.setText(Integer.toString(currentGame.getMoney()));
        
        ImageView item1Image = (ImageView) (marketItem1.getChildren().get(0));
        Label item1Label = (Label) (marketItem1.getChildren().get(1));
        item1Image.setImage(new Image("/main/resources/corn.png"));
        item1Label.setText("$" + myGame.getSeedPrice());

        ImageView item2Image = (ImageView) (marketItem2.getChildren().get(0));
        Label item2Label = (Label) (marketItem2.getChildren().get(1));
        item2Image.setImage(new Image("/main/resources/watermelon.png"));
        item2Label.setText("$" + myGame.getSeedPrice());

        ImageView item3Image = (ImageView) (marketItem3.getChildren().get(0));
        Label item3Label = (Label) (marketItem3.getChildren().get(1));
        item3Image.setImage(new Image("/main/resources/onion.png"));
        item3Label.setText("$" + myGame.getSeedPrice());

        ImageView item4Image = (ImageView) (marketItem4.getChildren().get(0));
        Label item4Label = (Label) (marketItem4.getChildren().get(1));
        item4Image.setImage(new Image("/main/resources/potatoes.png"));
        item4Label.setText("$" + myGame.getSeedPrice());

        ImageView item5Image = (ImageView) (marketItem5.getChildren().get(0));
        Label item5Label = (Label) (marketItem5.getChildren().get(1));
        item5Image.setImage(new Image("/main/resources/Fertilizer.png"));
        item5Label.setText("$" + myGame.getSeedPrice());

        ImageView item6Image = (ImageView) (marketItem6.getChildren().get(0));
        Label item6Label = (Label) (marketItem6.getChildren().get(1));
        item6Image.setImage(new Image("/main/resources/Pesticide.png"));
        item6Label.setText("$" + myGame.getSeedPrice());

        ImageView item7Image = (ImageView) (marketItem7.getChildren().get(0));
        Label item7Label = (Label) (marketItem7.getChildren().get(1));
        item7Image.setImage(new Image("/main/resources/tractor.png"));
        item7Label.setText("$5000");

        ImageView item8Image = (ImageView) (marketItem8.getChildren().get(0));
        Label item8Label = (Label) (marketItem8.getChildren().get(1));
        item8Image.setImage(new Image("/main/resources/irrigation.png"));
        item8Label.setText("$5000");
    }

    public void switchToInventory(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/screens/marketInventory_ui.FXML"));
        Parent marketInventory = loader.load();
        Scene startMarketInventory = new Scene(marketInventory);

        MarketInventoryUiController controller = loader.getController();
        controller.initData(myGame);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setTitle("Market: Sell");
        window.setScene(startMarketInventory);
        window.show();
    }

    public void setSelectedItem(MouseEvent mouseEvent) {
        String id = ((Node) mouseEvent.getSource()).getId();
        int slotId = Integer.parseInt(id.substring(10)) - 1;
        if (slotId == 0) {
            selectedItemName.setText("Corn Seed");
            selectedItemImage.setImage(new Image("/main/resources/corn.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 1) {
            selectedItemName.setText("Watermelon Seed");
            selectedItemImage.setImage(new Image("/main/resources/watermelon.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 2) {
            selectedItemName.setText("Onion Seed");
            selectedItemImage.setImage(new Image("/main/resources/onion.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 3) {
            selectedItemName.setText("Potato Seed");
            selectedItemImage.setImage(new Image("/main/resources/potatoes.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 4) {
            selectedItemName.setText("Fertilizer");
            selectedItemImage.setImage(new Image("/main/resources/Fertilizer.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 5) {
            selectedItemName.setText("Pesticide");
            selectedItemImage.setImage(new Image("/main/resources/Pesticide.png"));
            selectedItemPrice.setText(Integer.toString(myGame.getSeedPrice()));
        } else if (slotId == 6) {
            selectedItemName.setText("Tractor");
            selectedItemImage.setImage(new Image("/main/resources/tractor.png"));
            selectedItemPrice.setText("5000");
        }else if (slotId == 7) {
            selectedItemName.setText("Irrigation");
            selectedItemImage.setImage(new Image("/main/resources/irrigation.png"));
            selectedItemPrice.setText("5000");
        }
    }

    public void buyItem(ActionEvent actionEvent) {
        int buyAmount = Integer.parseInt(selectedItemQuantity.getText());
        String selectedItem = selectedItemName.getText();
        System.out.println(selectedItemName.getText());
        int price = myGame.getInventoryMap().get(selectedItem).getBasePrice();
        System.out.println(price);
        myGame.buyFromMarket(selectedItem, buyAmount, price);
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
