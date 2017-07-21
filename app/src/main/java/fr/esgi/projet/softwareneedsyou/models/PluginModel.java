package fr.esgi.projet.softwareneedsyou.models;

public class PluginModel {
    private int id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "PluginModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
