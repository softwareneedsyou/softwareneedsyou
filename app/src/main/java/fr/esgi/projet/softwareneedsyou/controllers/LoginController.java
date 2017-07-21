package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

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
