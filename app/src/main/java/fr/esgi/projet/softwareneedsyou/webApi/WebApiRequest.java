package fr.esgi.projet.softwareneedsyou.webApi;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import static fr.esgi.projet.softwareneedsyou.webApi.Globals.rootUrl;

public class WebApiRequest {

    public void ping(Callback<String> pingCallback) {
        Unirest.get(rootUrl + "/ping")
                .asStringAsync(pingCallback);
    }

    public void getChapters(Callback<JsonNode> getChaptersCallback) {
        Unirest.get(rootUrl + "/chapters")
                .asJsonAsync(getChaptersCallback);
    }

    public void getChapter(Callback<JsonNode> getChapterCallback, int id) {
        Unirest.get(rootUrl + "/chapters/" + id)
                .asJsonAsync(getChapterCallback);
    }

    public void getStory(Callback<JsonNode> getStory, int id){
        Unirest.get(rootUrl + "/stories/" + id)
                .asJsonAsync(getStory);
    }

    public void getStories(Callback<JsonNode> getStories){
        Unirest.get(rootUrl + "/stories")
                .asJsonAsync(getStories);
    }

    public void getPlugin(Callback<JsonNode> getPlugin, int id){
        Unirest.get(rootUrl + "/plugins/" + id)
                .asJsonAsync(getPlugin);
    }

    public void getPlugins(Callback<JsonNode> getPlugins){
        Unirest.get(rootUrl + "/plugins")
                .asJsonAsync(getPlugins);
    }

    public void getUser(Callback<JsonNode> getUser, int id){
        Unirest.get(rootUrl + "/users/" + id)
                .asJsonAsync(getUser);
    }

    public void login(Callback<JsonNode> loginCallback, String username, String password) {
        CredentialsProvider cp = new BasicCredentialsProvider();
        cp.setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(username, password));

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setDefaultCredentialsProvider(cp)
                .build();

        Unirest.setAsyncHttpClient(httpAsyncClient);

        Unirest.get(rootUrl + "/login")
                .asJsonAsync(loginCallback);
    }
}
