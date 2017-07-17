package fr.esgi.projet.softwareneedsyou;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader rootLoader = new FXMLLoader();
        Parent root = rootLoader.load(getClass().getResource("/Start.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Software Needs You");
        stage.setScene(scene);
        stage.show();
    }
}

