package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.api.history.*;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ChaptersController {
    ArrayList<Story> stories = new ArrayList<>();
    ArrayList<Chapter> chapters = new ArrayList<>();
    ArrayList<StoryRow> rows = new ArrayList<>();
    @FXML
    private ListView<Chapter> chaptersListView;
    @FXML
    private VBox storiesVBox;
    @FXML
    private AnchorPane chapterPane;
    private DataModel model;
    private ObservableList<Chapter> phobs;
    private Map<PluginHistoryDeclare, Collection<Chapter>> histories;
    private GameController gameController;
    private MainController mainController;

    @FXML
    public void initialize() {
        phobs = FXCollections.observableArrayList();
    }

    public void handleOnRowClickAction(MouseEvent e) {
        storiesVBox.getChildren().clear();
        rows.clear();
        Chapter currentChapter = chaptersListView.getSelectionModel().getSelectedItem();
        for (Story story : currentChapter.getStories()) {
            StoryRow row = new StoryRow();
            Button rowButton = (Button) row.getChildren().get(1);
            rowButton.setOnAction(actionEvent -> {
                gameController.initGame(currentChapter, story);
                mainController.switchGamePane();
            });
            row.setStoryName(story.getTitle());
            rows.add(row);
            VBox.setVgrow(row, Priority.NEVER);
        }
        storiesVBox.getChildren().setAll(rows);
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;

        @NonNull final HistoryLoader hl = new HistoryLoader();
        hl.loadAllPlugins();
        histories = hl.getHistories();
        for (Collection<Chapter> c : histories.values()) {
            chapters.addAll(c);
        }
        phobs.addAll(chapters);
        chaptersListView.setItems(phobs);
    }

    public void handleRefreshAction(ActionEvent actionEvent) {
    }

    public void handleLoadAction(ActionEvent actionEvent) {

    }

    public void getGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void getMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
