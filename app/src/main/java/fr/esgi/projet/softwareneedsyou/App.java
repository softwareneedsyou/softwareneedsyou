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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class App extends Application {
    public final static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        init_app();

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        Parent root = loginLoader.load();
        MainController loginController = loginLoader.getController();

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
        try {
			addPath(SharedParams.AppPluginsFolder + File.separator + "*");
		} catch (NoSuchMethodException | SecurityException | MalformedURLException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | URISyntaxException e) {
			e.printStackTrace();
		}
        System.out.println("\nNew classpath :\n" + System.getProperty("java.class.path"));
        
        try {
			Files.list(SharedParams.AppPluginsFolder).forEach(p -> {
				try {
					addPath(p.toFile());
				} catch (NoSuchMethodException | SecurityException | MalformedURLException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException | URISyntaxException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println();
        System.out.println(Arrays.toString(((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs()));
    }

    public static void addPath(@NonNull final File p) throws NoSuchMethodException, SecurityException, MalformedURLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, URISyntaxException {
    	@NonNull final URL u = p.toURI().toURL();
    	@NonNull final URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    	@NonNull final Class<URLClassLoader> urlClass = URLClassLoader.class;
    	@NonNull final Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
    	method.setAccessible(true);
    	method.invoke(urlClassLoader, new Object[]{u});
    }

    public static void addPath(@NonNull final String p) throws NoSuchMethodException, SecurityException, MalformedURLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, URISyntaxException {
    	addPath(new File(p));
    }
}