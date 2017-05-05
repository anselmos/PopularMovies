package com.github.anselmos.popularmovies.async;

import com.github.anselmos.popularmovies.models.Trailer;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import org.json.JSONException;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by anselmos on 28.04.17.
 */
//TODO USE RETROFIT & GSON instead ! http://guides.codepath.com/android/leveraging-the-gson-library
public class FetchTrailersAsyncTask extends AsyncTask<String, Integer, ArrayList<Trailer>> {
    
    @Override
    protected ArrayList<Trailer> doInBackground(final String[] params) {
        
        String apiKey = params[0];
        String movieId = params[1];
    
        ArrayList<Trailer> trailers = null;
        try {
            trailers = new ApiAccess().getTrailers(apiKey, movieId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;
    }

}
