package fr.esgi.projet.softwareneedsyou.controllers;


import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.webApi.Ping;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by brick on 16/07/17.
 */
public class StartLayoutController {
    @FXML private AnchorPane startPane;

    @FXML
    public void handleConnection(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ConnectionDialog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Connection");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegister() {
        Ping ping = new Ping();
        try {
            ping.ping();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) startPane.getScene().getWindow();
        stage.close();
    }
}
