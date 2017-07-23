package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML private Tab homeTab;
    @FXML private Tab storeTab;
    @FXML private Tab chaptersTab;
    @FXML private Tab gameTab;
    private DataModel model;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/HomeAnchorPane.fxml"));
        AnchorPane homeAnchorPane = homeLoader.load();
        HomeController homeController = homeLoader.getController();
        homeController.initModel(model);
        homeTab.setContent(homeAnchorPane);

        FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("/StoreAnchorPane.fxml"));
        AnchorPane storeAnchorPane = storeLoader.load();
        storeTab.setContent(storeAnchorPane);

        FXMLLoader chaptersLoader = new FXMLLoader(getClass().getResource("/ChaptersAnchorPane.fxml"));
        AnchorPane chaptersAnchorPane = chaptersLoader.load();
        chaptersTab.setContent(chaptersAnchorPane);

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/GameAnchorPane.fxml"));
        AnchorPane gameAnchorPane = gameLoader.load();
        gameTab.setContent(gameAnchorPane);
    }

    public void initModel(DataModel model){
        if(this.model != null){
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;
    }
}
