package fr.esgi.projet.softwareneedsyou.models;

public class StoryModel {
    private int id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "StoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
