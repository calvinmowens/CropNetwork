package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.image.MemoryImageSource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Paths;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        music();
        Parent root = FXMLLoader.load(getClass().getResource("screens/welcome_screen.fxml"));
        primaryStage.setTitle("Welcome Screen");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        launch(args);
    }

    MediaPlayer mediaPlayer;
    public void music() {

        File file = new File("src/main/resources/lofi.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setVolume(.2);
                mediaPlayer.setAutoPlay(true);
            }
        });
    }
}