package com.github.anselmos.popularmovies.async;

import com.github.anselmos.popularmovies.entity.jsonapi.Trailer;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by anselmos on 28.04.17.
 */
public class FetchTrailersAsyncTask extends AsyncTask<String, Integer, ArrayList<Trailer>> {
    
    @Override
    protected ArrayList<Trailer> doInBackground(final String[] params) {
        
        String apiKey = params[0];
        String movieId = params[1];
        
        return new ApiAccess().getTrailers(apiKey, movieId);
    }

}
