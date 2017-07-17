package fr.esgi.projet.softwareneedsyou.webApi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static fr.esgi.projet.softwareneedsyou.webApi.Globals.rootUrl;

/**
 * Created by brick on 16/07/17.
 */
public class Ping {
    public void ping() throws UnirestException {
        HttpResponse<JsonNode> chapters = Unirest.get(rootUrl)
                .asJson();
        System.out.println(chapters.getBody());
    }
}
