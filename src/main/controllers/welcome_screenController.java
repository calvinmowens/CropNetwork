<<<<<<< HEAD
package main.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class welcome_screenController {

    public void changeScreen(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../screens/game_config.FXML"));
        Scene scene = new Scene(root);

        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }
}
=======
package main.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class welcome_screenController {

    public void changeScreen(MouseEvent mouseEvent) throws IOException {
//        System.out.println(getClass().getResource("/main/screens/game_config.FXML"));
        Parent root = FXMLLoader.load(getClass().getResource("/main/screens/game_config.FXML"));
        Scene scene = new Scene(root);

        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }
}
>>>>>>> calvinsBranch
