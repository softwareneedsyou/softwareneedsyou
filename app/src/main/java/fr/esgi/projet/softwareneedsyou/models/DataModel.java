package fr.esgi.projet.softwareneedsyou.models;

import java.util.ArrayList;

public class DataModel {

    private PingModel pong;
    private ChapterModel currentChapter;
    private StoryModel currentStory;
    private ArrayList<ChapterModel> chapters;
    private ArrayList<PluginModel> plugins;
    private UserModel user;

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
    }

    private ArrayList<StoryModel> stories;

    public DataModel() {
        setCurrentChapter(this.currentChapter);
        setCurrentStory(this.currentStory);
    }

    public ArrayList<PluginModel> getPlugins() {
        return plugins;
    }

    public ArrayList<ChapterModel> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<ChapterModel> chapters) {
        this.chapters = chapters;
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
