package com.github.anselmos.popularmovies.models.enums;

/**
 * Created by anselmos on 28.04.17.
 */
public enum BUILD_URL_TYPE {
    
    MOST_POPULAR("popular"),
    TOP_RATED("top_rated"),
    FAVOURITES_USER("not_api_internal_user_favourites"),
    TRAILER("videos"),
    REVIEW("reviews");
    
    public String value;
    private BUILD_URL_TYPE(String value){
        this.value = value;
    }
    

}
