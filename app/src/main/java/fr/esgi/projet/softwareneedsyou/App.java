package fr.esgi.projet.softwareneedsyou;

import fr.esgi.projet.softwareneedsyou.controllers.LoginController;
import fr.esgi.projet.softwareneedsyou.controllers.MainController;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App extends Application {
    public final static void main(final String[] args) {
        launch(args);
    }



    @Override
    public void start(final Stage stage) throws Exception {

        init_app();

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loginLoader.load();
        LoginController loginController = loginLoader.getController();

        DataModel model = new DataModel();
        loginController.initModel(model);

        Scene scene = new Scene(root);

        stage.setTitle("Software Needs You");
        stage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("app_ico.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialise la JVM avant le lancement de l'application
     */

    public final static void init_app() {

        //create folder if not existe
        if(Files.notExists(SharedParams.AppParamsFolder)) {
            System.out.println("// create app folders");
            try {
                Files.createDirectories(SharedParams.AppParamsFolder);
                Files.createDirectories(SharedParams.AppPluginsFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else

            System.out.println("// App folder exists");

        // set classpath

        String jcp = System.getProperty("java.class.path");

        System.out.println("Old classpath : " + jcp);

        System.setProperty("java.class.path", jcp + File.pathSeparator + SharedParams.AppPluginsFolder + File.separator + "*");

    }

}