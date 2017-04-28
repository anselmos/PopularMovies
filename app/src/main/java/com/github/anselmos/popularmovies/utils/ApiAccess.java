package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.entity.enums.BUILD_URL_TYPE;
import com.github.anselmos.popularmovies.entity.enums.ImageSize;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anselmos on 07.04.17.
 */
public class ApiAccess {
    
    public ArrayList<PopularEntity> getMovies(String apiKey, BUILD_URL_TYPE sortBy) throws JSONException {
        String url = new UrlBuilder().build(sortBy, apiKey);
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
        try {
            arrayResults = (JSONArray) jsonObject.get("results");
        } catch (JSONException e) {
            
            e.printStackTrace();
        }
        return decodeJSONArray(arrayResults);
    }
    
    public ArrayList<PopularEntity> decodeJSONArray(JSONArray array) throws JSONException {
        /**
         * Decodes data from JSONArray to popularEntity
         */
        ArrayList<PopularEntity> popularEntities = new ArrayList<PopularEntity>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObject = array.getJSONObject(i);
            PopularEntity movie = new PopularEntity(arrayObject);
            popularEntities.add(movie);
        }
        return popularEntities;
    }
    
    public static void insertImageInView(final Context context, final ImageView imageView, final String imagePath, ImageSize imageSize) {
        switch (imageSize) {
            case SMALL:
                Picasso.with(context).load(new PosterUrlBuilder().getSmallImage(imagePath)).into(imageView);
                break;
            case MEDIUM:
                Picasso.with(context).load(new PosterUrlBuilder().getMediumImage(imagePath)).into(imageView);
                break;
            case LARGE:
                Picasso.with(context).load(new PosterUrlBuilder().getLargeImage(imagePath)).into(imageView);
                break;
        }
    }
}

