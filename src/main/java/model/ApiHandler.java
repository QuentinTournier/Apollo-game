package model;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ApiHandler {
    private final String API_KEY = "6afe490665abcb64d04ffb0f0e384df6";

    public Game getAGame(int gameId){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get("https://api-2445582011268.apicast.io/games/" + gameId +"?fields=*")
                    .header("user-key", API_KEY)
                    .header("Accept", "application/json")
                    .asJson();
            Gson gson = new Gson();
            return gson.fromJson(String.valueOf(response.getBody()), Game[].class)[0];
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game[] getAllGames(){
        HttpResponse<JsonNode> response = null;
        int limit = 10;
        try {
            response = Unirest.get("https://api-2445582011268.apicast.io/games/?fields=*&limit=" + limit)
                    .header("user-key", API_KEY)
                    .header("Accept", "application/json")
                    .asJson();

            Gson gson = new Gson();
            return gson.fromJson(String.valueOf(response.getBody()), Game[].class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game[] searchGame(String name){
        HttpResponse<JsonNode> response = null;
        try {
            String nameSearch = URLEncoder.encode(name, "UTF-8");
            response = Unirest.get("https://api-2445582011268.apicast.io/games/?search=" + nameSearch + "&fields=*")
                    .header("user-key", API_KEY)
                    .header("Accept", "application/json")
                    .asJson();

            Gson gson = new Gson();
            Game[] games = gson.fromJson(String.valueOf(response.getBody()), Game[].class);
            if(games.length < 1){
                return null;
            }
            else return games;
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
