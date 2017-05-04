package com.github.anselmos.popularmovies.async;

import com.github.anselmos.popularmovies.entity.jsonapi.Review;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import org.json.JSONException;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by anselmos on 28.04.17.
 */
//TODO USE RETROFIT & GSON instead ! http://guides.codepath.com/android/leveraging-the-gson-library
public class FetchReviewsAsyncTask extends AsyncTask<String, Integer, ArrayList<Review>> {
    
    @Override
    protected ArrayList<Review> doInBackground(final String[] params) {
        
        String apiKey = params[0];
        String movieId = params[1];
    
        ArrayList<Review> reviews = null;
        try {
            reviews = new ApiAccess().getReviews(apiKey, movieId);
        } catch (JSONException e) {
            e.printStackTrace();
            
        }
        return reviews;
    }

}
