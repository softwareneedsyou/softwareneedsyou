package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private FlowPane startFlowPane;
    @FXML private AnchorPane startAnchorPane;

    private DataModel model;

    public void initModel(DataModel model) {
        if(this.model != null) {
            throw new IllegalStateException("There can be only one model.");
        }
        this.model = model;
    }

    @FXML
    public void initialize(){
    }

    @FXML
    public void handleConnection(ActionEvent event) {
    }

    @FXML
    public void handleRegister(ActionEvent event) {
    }

    @FXML
    public void handleClose(ActionEvent event) {

    }
}
