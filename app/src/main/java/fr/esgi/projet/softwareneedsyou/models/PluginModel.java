package fr.esgi.projet.softwareneedsyou.models;

public class PluginModel {
    private int id;
    private String name;
    private PluginType pluginType;
    private String type;
    private String description;

    public PluginModel(int id, String name, String description, PluginType pluginType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pluginType = pluginType;

    }

    @Override
    public String toString() {
        return "PluginModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public PluginType getPluginType() {
        return pluginType;
    }

    public String getType(){
        return "blah";
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    class PluginType {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
