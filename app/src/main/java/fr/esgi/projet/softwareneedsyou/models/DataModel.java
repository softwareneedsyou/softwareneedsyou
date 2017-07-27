package fr.esgi.projet.softwareneedsyou.models;

import fr.esgi.projet.softwareneedsyou.api.history.Chapter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;

public class DataModel {

    private PingModel pong;
    private Collection<Chapter> currentChapter;
    private StoryModel currentStory;
    private ArrayList<ChapterModel> chapters = new ArrayList<ChapterModel>();
    private ObservableList<ChapterModel> chaptersList = FXCollections.observableList(chapters);
    private ArrayList<StoryModel> stories = new ArrayList<StoryModel>();
    private ObservableList<StoryModel> storiesList = FXCollections.observableList(stories);
    private ArrayList<PluginModel> plugins = new ArrayList<>();
    private UserModel user;

    public ObservableList<StoryModel> getStoriesList() {
        return storiesList;
    }

    public ObservableList<ChapterModel> getChaptersList() {
        return chaptersList;
    }

    public void setStoriesList(ObservableList<StoryModel> storiesList) {
        this.storiesList = storiesList;
    }

    public void setChaptersList(ObservableList<ChapterModel> chaptersList) {
        this.chaptersList = chaptersList;
    }

    public PingModel getPong() {
        return pong;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setPong(PingModel pong) {
        this.pong = pong;
    }

    public ArrayList<StoryModel> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StoryModel> stories) {
        this.stories = stories;
        Platform.runLater(() -> {
            this.storiesList.setAll(stories);
        });
    }

    public DataModel() {
        setCurrentStory(this.currentStory);
    }

    public ArrayList<PluginModel> getPlugins() {
        return plugins;
    }

    /*
    public ArrayList<ChapterModel> getChapters() {
        return this.chapters;
    }
    */

    public void setChapters(ArrayList<ChapterModel> chapters) {
        this.chapters = chapters;
        Platform.runLater(() -> {
            this.chaptersList.setAll(chapters);
        });
    }

    public PluginModel getCurrentPlugin() {
        return currentPlugin;
    }

    public void setCurrentPlugin(PluginModel currentPlugin) {
        this.currentPlugin = currentPlugin;
    }

    private PluginModel currentPlugin;

    public StoryModel getCurrentStory() {
        return currentStory;
    }

    public void setCurrentStory(StoryModel currentStory) {
        this.currentStory = currentStory;
    }

    public Collection<Chapter> getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(Collection<Chapter> currentChapter) {
        this.currentChapter = currentChapter;
    }

    public void setPlugins(ArrayList<PluginModel> plugins) {
        this.plugins = plugins;
    }
}
