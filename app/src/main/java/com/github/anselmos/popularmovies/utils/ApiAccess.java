package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.models.enums.BUILD_URL_TYPE;
import com.github.anselmos.popularmovies.models.enums.ImageSize;
import com.github.anselmos.popularmovies.models.PopularEntity;
import com.github.anselmos.popularmovies.models.Review;
import com.github.anselmos.popularmovies.models.Trailer;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anselmos on 07.04.17.
 */
public class ApiAccess {
    
    public ArrayList<Trailer> getTrailers(String apiKey, String movieId) throws JSONException {
        String url = new UrlBuilder().build(BUILD_URL_TYPE.TRAILER, apiKey, movieId);
        OkHttpClient client = new OkHttpClient();
    
        Response response = decodeResponse(url, client);
        JSONObject jsonObject = decodeJSONObjectFromResponse(response);
        JSONArray arrayResults = getJSONObject(jsonObject, "results");
        return decodeTrailers(arrayResults);
    }
    public ArrayList<Review> getReviews(String apiKey, String movieId) throws JSONException {
        String url = new UrlBuilder().build(BUILD_URL_TYPE.REVIEW, apiKey, movieId);
        OkHttpClient client = new OkHttpClient();
        
        Response response = decodeResponse(url, client);
        JSONObject jsonObject = decodeJSONObjectFromResponse(response);
        JSONArray arrayResults = getJSONObject(jsonObject, "results");
        return decodeReviews(arrayResults);
    }
    public ArrayList<PopularEntity> getMovies(String apiKey, BUILD_URL_TYPE sortBy) throws JSONException {
        String url = new UrlBuilder().build(sortBy, apiKey);
        OkHttpClient client = new OkHttpClient();
        
        Response response = decodeResponse(url, client);
        JSONObject jsonObject = decodeJSONObjectFromResponse(response);
        JSONArray arrayResults = getJSONObject(jsonObject, "results");
        return decodePopularEntities(arrayResults);
    }
    
    @Nullable
    public JSONArray getJSONObject(final JSONObject jsonObject, String getFromJSONArray) {
        JSONArray arrayResults = null;
        try {
            arrayResults = (JSONArray) jsonObject.get(getFromJSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayResults;
    }
    
    @Nullable
    public Response decodeResponse(final String url, final OkHttpClient client) {
        Response response = null;
        try {
            response = client.newCall(
                    new Request.Builder().url(url).build()
            ).execute();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return response;
    }
    
    @Nullable
    public JSONObject decodeJSONObjectFromResponse(final Response response) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.body().string());
        } catch (JSONException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return jsonObject;
    }
    
    public ArrayList<Trailer> decodeTrailers(JSONArray array) throws JSONException {
        /**
         * Decodes data from JSONArray to trailers
         */
        ArrayList<Trailer> trailers = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObject = array.getJSONObject(i);
            Trailer trailer = new Trailer(arrayObject);

            trailers.add(trailer);
        }
        return trailers;
    }
    
    public ArrayList<Review> decodeReviews(JSONArray array) throws JSONException {
        /**
         * Decodes data from JSONArray to reviews
         */
        ArrayList<Review> reviews = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObject = array.getJSONObject(i);
            Review review = new Review(arrayObject);
            reviews.add(review);
        }
        return reviews;
    }
    
    public ArrayList<PopularEntity> decodePopularEntities(JSONArray array) throws JSONException {
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

