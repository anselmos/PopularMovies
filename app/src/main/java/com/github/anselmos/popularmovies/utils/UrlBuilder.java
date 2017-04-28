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
        /**
         * Overloads build method with default value of movieId =""
         */
        return this.build(sortType, apiKey, "");
    }
    public String build(BUILD_URL_TYPE urlType, String apiKey, String movieId){
        /**
         * Main url builder
         *
         * @parameter urlType - uses BUILD_URL_TYPE enums
         */
        
        Uri.Builder builder = this.buildBaseUrl();
        switch(urlType){
            case MOST_POPULAR:
            case TOP_RATED:
                builder.appendPath(urlType.value);
                break;
            case TRAILER:
            case REVIEW:
                builder.appendPath(movieId);
                builder.appendPath(urlType.value);
        }
        
        builder.appendQueryParameter(QUERY_API_KEY, apiKey);
        return builder.toString();
        
    }
}
