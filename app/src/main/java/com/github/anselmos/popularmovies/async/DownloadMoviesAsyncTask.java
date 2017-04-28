package com.github.anselmos.popularmovies.async;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.ApiAccess;
import com.github.anselmos.popularmovies.utils.MoviesDoInBackgroundParameter;
import com.github.anselmos.popularmovies.utils.UrlBuilder;

import org.json.JSONException;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by anselmos on 07.04.17.
 */
public class DownloadMoviesAsyncTask extends AsyncTask<MoviesDoInBackgroundParameter, Integer, ArrayList<PopularEntity>> {
    
    protected ArrayList<PopularEntity> doInBackground(MoviesDoInBackgroundParameter... parameters){
        MoviesDoInBackgroundParameter param = parameters[0];
        ArrayList<PopularEntity> movies =null;
        try {
             movies = new ApiAccess().getMovies(param.getKey(), param.getSortBy());
            
        }catch(JSONException e){
            
        }
        return movies;
    }
    
}
