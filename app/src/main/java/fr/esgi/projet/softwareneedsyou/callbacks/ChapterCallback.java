package fr.esgi.projet.softwareneedsyou.callbacks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.models.ChapterModel;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.collections.ObservableList;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChapterCallback {

    /*
    public Callback<JsonNode> getChapterCallback(DataModel model){
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String json = response.getBody().toString();
                ChapterModel chapter = new Gson().fromJson(json, ChapterModel.class);
                model.setCurrentChapter(chapter);
            }

            @Override
            public void failed(UnirestException e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("Request has been cancelled");
            }
        };
    }

    public Callback<JsonNode> getChaptersCallback(DataModel model) {
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String JSONChapters = response.getBody().toString();
                //@TIPS this is necessary since Java doesn't give a way to represent generic types
                Type tt = new TypeToken<ArrayList<ChapterModel>>(){}.getType();
                ArrayList<ChapterModel> chapters = new Gson().fromJson(JSONChapters, tt);
                    model.setChapters(chapters);
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
    */

}
