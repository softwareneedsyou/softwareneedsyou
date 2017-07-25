package fr.esgi.projet.softwareneedsyou.models;

public class ChapterModel {
    private int id;

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
