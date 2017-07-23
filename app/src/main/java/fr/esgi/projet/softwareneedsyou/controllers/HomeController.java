package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.UserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label lastnameLabel;
    private StringProperty lastnameProperty;
    @FXML
    private Label firstnameLabel;
    private StringProperty firstnameProperty;
    @FXML
    private Label usernameLabel;
    private StringProperty usernameProperty ;
    @FXML
    private Label emailLabel;
    private StringProperty emailProperty;
    private DataModel model;

    @FXML
    public void initialize(){
        lastnameProperty = new SimpleStringProperty();
        lastnameProperty.addListener((observableValue, s, t1) -> lastnameLabel.setText(observableValue.getValue()));
        firstnameProperty = new SimpleStringProperty();
        firstnameProperty.addListener((observableValue, s, t1) -> firstnameLabel.setText(observableValue.getValue()));
        usernameProperty = new SimpleStringProperty();
        usernameProperty.addListener((observableValue, s, t1) -> usernameLabel.setText(observableValue.getValue()));
        emailProperty = new SimpleStringProperty();
        emailProperty.addListener((observableValue, s, t1) -> emailLabel.setText(observableValue.getValue()));
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;

        UserModel user = model.getUser();
        lastnameProperty.setValue(user.getLastname());
        firstnameProperty.setValue(user.getFirstname());
        usernameProperty.setValue(user.getUsername());
        emailProperty.setValue(user.getEmail());
    }
}
