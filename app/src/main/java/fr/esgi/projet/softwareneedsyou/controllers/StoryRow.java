package fr.esgi.projet.softwareneedsyou.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StoryRow extends AnchorPane {
    @FXML
    private Label storyRowLabel;

    public StoryRow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoryRow.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStoryName() {
        return storyRowLabel.getText();
    }

    public StringProperty storyNameProperty() {
        return storyRowLabel.textProperty();
    }

    public void setStoryName(String storyName) {
        storyNameProperty().setValue(storyName);
    }

    @FXML
    public void handlePlayAction(ActionEvent e){
        System.out.println("Play");
    }
}
