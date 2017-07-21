package fr.esgi.projet.softwareneedsyou.callbacks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.StoryModel;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoriesCallback {
    public Callback<JsonNode> getStory(DataModel model) {
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String JSONStory = response.getBody().toString();
                StoryModel story = new Gson().fromJson(JSONStory, StoryModel.class);
                model.setCurrentStory(story);
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

    public Callback<JsonNode> getStories(DataModel model) {
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String JSONStories = response.getBody().toString();
                //@TIPS this is necessary since Java doesn't give a way to represent generic types
                Type tt = new TypeToken<ArrayList<StoryModel>>(){}.getType();
                ArrayList<StoryModel> stories = new Gson().fromJson(JSONStories, tt);
                model.setStories(stories);
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
