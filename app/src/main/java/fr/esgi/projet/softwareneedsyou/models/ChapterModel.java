package fr.esgi.projet.softwareneedsyou.models;

import java.util.ArrayList;

public class ChapterModel {
    private int id;

    public ArrayList<StoryModel> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StoryModel> stories) {
        this.stories = stories;
    }

    private ArrayList<StoryModel> stories;

    public ChapterModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ChapterModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String name;
    private String description;

}
