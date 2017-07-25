package fr.esgi.projet.softwareneedsyou.models;

public class StoryModel {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public StoryModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "StoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
