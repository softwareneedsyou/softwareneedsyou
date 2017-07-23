package fr.esgi.projet.softwareneedsyou.callbacks;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.UserModel;

public class UserCallback {

    public Callback<JsonNode> getUserCallback(DataModel model){
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                String json = response.getBody().toString();
                UserModel user = new Gson().fromJson(json, UserModel.class);
                model.setUser(user);
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

    public Callback<JsonNode> loginCallback(DataModel model){
        return new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                model.setUser(new Gson().fromJson(response.getBody().toString(), UserModel.class));
            }

            @Override
            public void failed(UnirestException e) {
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("The request has been cancelled.");
            }
        };
    }

}