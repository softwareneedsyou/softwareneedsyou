package fr.esgi.projet.softwareneedsyou.models;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataModel {

    private PingModel pong;
    private ChapterModel currentChapter;
    private StoryModel currentStory;
    private ArrayList<ChapterModel> chapters = new ArrayList<ChapterModel>();
    private ObservableList<ChapterModel> chaptersList = FXCollections.observableList(chapters);
    private ArrayList<StoryModel> stories = new ArrayList<StoryModel>();
    private ObservableList<StoryModel> storiesList = FXCollections.observableList(stories);
    private ArrayList<PluginModel> plugins;

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
        chapters.add(new ChapterModel());
        stories.add(new StoryModel());
        setCurrentChapter(this.currentChapter);
        setCurrentStory(this.currentStory);
    }

    public ArrayList<PluginModel> getPlugins() {
        return plugins;
    }

    public ArrayList<ChapterModel> getChapters() {
        return this.chapters;
    }

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

    public ChapterModel getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(ChapterModel currentChapter) {
        this.currentChapter = currentChapter;
    }

    public void setPlugins(ArrayList<PluginModel> plugins) {
        this.plugins = plugins;
    }
}
