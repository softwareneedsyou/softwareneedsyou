package fr.esgi.projet.softwareneedsyou.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.callbacks.UserCallback;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    HomeController homeController;
    StoreController storeController;
    ChaptersController chaptersController;
    GameController gameController;
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

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/GameAnchorPane.fxml"));
        AnchorPane gameAnchorPane = gameLoader.load();
        gameController = gameLoader.getController();
        gameTab.setContent(gameAnchorPane);
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;

        WebApiRequest war = new WebApiRequest();
        try {
            war.login(new UserCallback(), model, "brick", "iliketrains");
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        homeController.initModel(model);
        storeController.initModel(model);
        chaptersController.initModel(model);
        gameController.initModel(model);
    }

    public void handleCloseAction(ActionEvent actionEvent) {
        mainViewAnchorPane.getScene().getWindow().hide();
    }

    public void handleAboutAction(ActionEvent actionEvent) {

    }
}