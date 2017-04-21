package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.MainActivity;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anselmos on 07.04.17.
 */

public class UrlBuilder {
    public final String API_SCHEME = "https";
    public final String API_AUTHORITY = "api.themoviedb.org";
    public final String API_VERSION = "3";
    public final String API_MOVIE = "movie";
    public final String API_TOP_RATED= "top_rated";
    public final String API_MOST_POPULAR = "popular";
    
    public final String QUERY_API_KEY = "api_key";
    
    

    public enum SORT_BY {
        MOST_POPULAR,
        TOP_RATED
    }

    public void UrlBuilder(){
        /**
         *
         * Just an empty constructor for this simplest class.
         */
    }
    public Uri.Builder buildBaseUrl(){
        /**
         * Builds base uri of The Movie DB and returns Builder
         */
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(API_SCHEME).authority(API_AUTHORITY)
                .appendPath(API_VERSION)
                .appendPath(API_MOVIE);
    }

    public String build(SORT_BY sortType, String apiKey){
        /**
         * Main url builder
         *
         * @parameter sortType - uses SORT_BY enums
         */
        
        Uri.Builder builder = this.buildBaseUrl();
        if(sortType == SORT_BY.MOST_POPULAR){
            
            builder.appendPath(API_MOST_POPULAR);
            
        }else if(sortType == SORT_BY.TOP_RATED){
            
            builder.appendPath(API_TOP_RATED);
            
        }
        builder.appendQueryParameter(QUERY_API_KEY, apiKey);
        return builder.toString();
        
    }
}
