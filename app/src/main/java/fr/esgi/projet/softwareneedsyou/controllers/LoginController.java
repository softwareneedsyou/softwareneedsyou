package fr.esgi.projet.softwareneedsyou.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.callbacks.UserCallback;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONException;


public class LoginController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Label errorLabel;
    private StringProperty errorMessage;
    private DataModel model;

    @FXML
    public void initialize() {
        errorMessage = new SimpleStringProperty();
        errorMessage.addListener((observableValue, s, t1) -> errorLabel.setText(observableValue.getValue()));
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can be only one model.");
        }
        this.model = model;
    }

    @FXML
    public void handleOk() {
        String username = String.valueOf(usernameTextField.getCharacters());
        String password = String.valueOf(passwordPasswordField.getCharacters());
        WebApiRequest war = new WebApiRequest();
        try {
            war.login(new UserCallback(), model, username, password);
            errorMessage.setValue("");
        } catch (JSONException | UnirestException e) {
            errorMessage.setValue("Try again");
        }
    }

    public void handleRedirection(ActionEvent actionEvent) {
        String url = "http://www.google.com"; //TODO: Change the url to the actual website
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try {
            if (os.indexOf("win") >= 0) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf("mac") >= 0) {
                rt.exec("open " + url);
            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                        "netscape", "opera", "links", "lynx"};

                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++) {
                    cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
                }

                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception e) {
            return;
        }
        return;
    }
}
