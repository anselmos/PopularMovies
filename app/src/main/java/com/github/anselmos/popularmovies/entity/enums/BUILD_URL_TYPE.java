package com.github.anselmos.popularmovies.entity.enums;

/**
 * Created by anselmos on 28.04.17.
 */
public enum BUILD_URL_TYPE {
    
    MOST_POPULAR("popular"),
    TOP_RATED("top_rated"),
    TRAILER("videos"),
    REVIEW("reviews");
    
    public String value;
    private BUILD_URL_TYPE(String value){
        this.value = value;
    }
    

}
