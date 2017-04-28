package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.entity.enums.BUILD_URL_TYPE;

import android.net.Uri;

/**
 * Created by anselmos on 07.04.17.
 */

public class UrlBuilder {
    public final String API_SCHEME = "https";
    public final String API_AUTHORITY = "api.themoviedb.org";
    public final String API_VERSION = "3";
    public final String API_MOVIE = "movie";
    
    public final String QUERY_API_KEY = "api_key";


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
    public String build(BUILD_URL_TYPE sortType, String apiKey){
        return this.build(sortType, apiKey, "");
    }
    public String build(BUILD_URL_TYPE sortType, String apiKey, String movieId){
        /**
         * Main url builder
         *
         * @parameter sortType - uses SORT_BY enums
         */
        
        Uri.Builder builder = this.buildBaseUrl();
        switch(sortType){
            case MOST_POPULAR:
            case TOP_RATED:
                builder.appendPath(sortType.value);
                break;
            case TRAILER:
            case REVIEW:
                builder.appendPath(movieId);
                builder.appendPath(sortType.value);
        }
        
        builder.appendQueryParameter(QUERY_API_KEY, apiKey);
        return builder.toString();
        
    }
}
