package fr.esgi.projet.softwareneedsyou;

import fr.esgi.projet.softwareneedsyou.controllers.LoginController;
import fr.esgi.projet.softwareneedsyou.controllers.MainController;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        /*
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loginLoader.load();
        LoginController loginController = loginLoader.getController();

        DataModel model = new DataModel();
        loginController.initModel(model);

        Scene scene = new Scene(root);

        stage.setTitle("Software Needs You");
        stage.setScene(scene);
        stage.show();
        */
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        AnchorPane root = mainLoader.load();
        MainController mainController = mainLoader.getController();

        DataModel model = new DataModel();
        mainController.initModel(model);

        Scene scene = new Scene(root);

        stage.setTitle("Software Needs You");
        stage.setScene(scene);
        stage.show();
    }
}