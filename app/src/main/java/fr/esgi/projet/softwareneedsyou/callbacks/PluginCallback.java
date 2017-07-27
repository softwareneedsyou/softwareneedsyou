package fr.esgi.projet.softwareneedsyou.callbacks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.PluginModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PluginCallback {
    public Callback<JsonNode> getPlugin(DataModel model){
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String JSONPlugin = response.getBody().toString();
                PluginModel plugin = new Gson().fromJson(JSONPlugin, PluginModel.class);
                model.setCurrentPlugin(plugin);
            }

            @Override
            public void failed(UnirestException e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("The request has been cancelled");
            }
        };
    }

    public Callback<JsonNode> getPlugins(DataModel model) {
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String JSONPlugins = response.getBody().toString();
                //@TIPS this is necessary since Java doesn't give a way to represent generic types
                Type tt = new TypeToken<ArrayList<PluginModel>>(){}.getType();
                ArrayList<PluginModel> plugins = new Gson().fromJson(JSONPlugins, tt);
                model.setPlugins(plugins);
            }

            @Override
            public void failed(UnirestException e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("The request has been cancelled");
            }
        };
    }
}
