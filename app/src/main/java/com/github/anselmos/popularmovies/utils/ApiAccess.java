package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.MainActivity;
import com.github.anselmos.popularmovies.entity.db.Movie;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.github.anselmos.popularmovies.utils.UrlBuilder.SORT_BY.*;

/**
 * Created by anselmos on 07.04.17.
 */
public class ApiAccess {
    
    public ArrayList<PopularEntity> getMovies(String apiKey) throws JSONException {
        String url = new UrlBuilder().build(MOST_POPULAR, apiKey);
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        try {
            response = client.newCall(
                    new Request.Builder().url(url).build()
            ).execute();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.body().string());
        } catch (JSONException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        ArrayList<PopularEntity> results = null;
    
        JSONArray arrayResults = null;
        try{
            arrayResults = (JSONArray) jsonObject.get("results");
            
        }catch(JSONException e){
    
            e.printStackTrace();
        }
        return decodeJSONArray(arrayResults);
    }
    public ArrayList<PopularEntity> decodeJSONArray(JSONArray array) throws JSONException{
        /**
         * Decodes data from JSONArray to popularEntity
         */
        ArrayList<PopularEntity> popularEntities = new ArrayList<PopularEntity>();
        for (int i=0; i < array.length(); i++){
            JSONObject arrayObject = array.getJSONObject(i);
            PopularEntity movie = new PopularEntity();
            movie.parseJSONObject(arrayObject);
            popularEntities.add(movie);
        }
        return popularEntities;
    }
    
}

