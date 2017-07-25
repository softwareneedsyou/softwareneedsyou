package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.ChapterModel;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.StoryModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.FocusModel;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChaptersController {
    ArrayList<ChapterModel> chaptersArrayList;
    ArrayList<StoryModel> storiesModelArrayList = new ArrayList<>();
    @FXML
    private ListView<ChapterModel> chaptersListView;
    @FXML
    private VBox storiesVBox;
    private DataModel model;
    private ObservableList<ChapterModel> chaptersObservableList;

    @FXML
    public void initialize() {
        chaptersListView.setItems(chaptersObservableList);
        chaptersObservableList = FXCollections.observableArrayList();

        for (StoryModel story : storiesModelArrayList) {
            StoryRow row = new StoryRow();
            row.setStoryName(story.getName());
            storiesVBox.getChildren().add(row);
            VBox.setVgrow(row, Priority.NEVER);
        }
    }

    public void handleOnRowClickAction(MouseEvent e){
        ChapterModel currentChapter = chaptersListView.getFocusModel().getFocusedItem();
        model.setCurrentChapter(currentChapter);
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;

        chaptersArrayList = new ArrayList<>();

        chaptersObservableList.addListener((ListChangeListener.Change<? extends ChapterModel> change) -> {
            chaptersListView.setItems((ObservableList<ChapterModel>) change.getList());
        });

        chaptersObservableList.setAll(chaptersArrayList);

    }

    public void handleRefreshAction(ActionEvent actionEvent) {
    }

    public void handleLoadAction(ActionEvent actionEvent) {
        ChapterModel chapterone = new ChapterModel("this is the first chapter", "welcome to this chapter!");
        chaptersArrayList.add(chapterone);
        chaptersObservableList.setAll(chaptersArrayList);
    }
}
