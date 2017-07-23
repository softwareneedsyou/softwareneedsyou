package fr.esgi.projet.softwareneedsyou.controllers;
import fr.esgi.projet.softwareneedsyou.callbacks.UserCallback;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;


public class LoginController {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
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
    public void handleOk() {
        System.out.println(model.getUser());
        String username = String.valueOf(usernameTextField.getCharacters());
        String password = String.valueOf(passwordPasswordField.getCharacters());
        WebApiRequest war = new WebApiRequest();
        war.login(new UserCallback().loginCallback(model), username, password);
    }
}
