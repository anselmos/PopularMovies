package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import org.json.JSONException;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by anselmos on 07.04.17.
 */
public class DownloadMoviesAsyncTask extends AsyncTask<String, Integer, ArrayList<PopularEntity>> {
    
    protected ArrayList<PopularEntity> doInBackground(String... apiKey){
        ArrayList<PopularEntity> movies =null;
        try {
             movies = new ApiAccess().getMovies(apiKey[0]);
        }catch(JSONException e){
            
        }
        return movies;
    }
    
}
