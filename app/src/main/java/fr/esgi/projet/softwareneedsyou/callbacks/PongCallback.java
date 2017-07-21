package fr.esgi.projet.softwareneedsyou.callbacks;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.PingModel;

public class PongCallback {
    public Callback<String> pingCallback(DataModel model){

        return new Callback<String>() {
            @Override
            public void completed(HttpResponse<String> response) {
                String JSONPong = response.getBody();
                PingModel ping = new Gson().fromJson(JSONPong, PingModel.class);
                model.setPong(ping);
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
}
