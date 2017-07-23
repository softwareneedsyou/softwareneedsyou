package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StoreController {
    @FXML AnchorPane storeAnchorPane;
    @FXML
    TableView pluginsTableView;
    private DataModel model;

    public void initModel(DataModel model) {
        if(this.model != null){
            throw new IllegalStateException("There can only be ont model");
        }
        this.model = model;
    }
}
