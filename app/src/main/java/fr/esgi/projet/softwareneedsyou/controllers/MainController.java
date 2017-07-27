package fr.esgi.projet.softwareneedsyou.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.callbacks.UserCallback;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    HomeController homeController;
    StoreController storeController;
    ChaptersController chaptersController;
    GameController gameController;
    @FXML
    private TabPane panes;
    @FXML
    private AnchorPane mainViewAnchorPane;
    @FXML
    private Tab homeTab;
    @FXML
    private Tab storeTab;
    @FXML
    private Tab chaptersTab;
    @FXML
    private Tab gameTab;
    private DataModel model;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/HomeAnchorPane.fxml"));
        AnchorPane homeAnchorPane = homeLoader.load();
        homeController = homeLoader.getController();
        homeTab.setContent(homeAnchorPane);

        FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("/StoreAnchorPane.fxml"));
        AnchorPane storeAnchorPane = storeLoader.load();
        storeController = storeLoader.getController();
        storeTab.setContent(storeAnchorPane);

        FXMLLoader chaptersLoader = new FXMLLoader(getClass().getResource("/ChaptersAnchorPane.fxml"));
        AnchorPane chaptersAnchorPane = chaptersLoader.load();
        chaptersController = chaptersLoader.getController();
        chaptersTab.setContent(chaptersAnchorPane);

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/GamePane.fxml"));
        BorderPane gameAnchorPane = gameLoader.load();
        gameController = gameLoader.getController();
        gameTab.setContent(gameAnchorPane);
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;

        homeController.initModel(model);
        storeController.initModel(model);
        chaptersController.initModel(model);
        chaptersController.getGameController(gameController);
        chaptersController.getMainController(this);
        gameController.initModel(model);
    }

    public void handleCloseAction(ActionEvent actionEvent) {
        mainViewAnchorPane.getScene().getWindow().hide();
    }

    public void handleAboutAction(ActionEvent actionEvent) {

    }

    public void switchGamePane() {
        panes.getSelectionModel().select(gameTab);
    }
}
