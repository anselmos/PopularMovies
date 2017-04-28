package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.entity.enums.BUILD_URL_TYPE;

/**
 * Created by anselmos on 21.04.17.
 */
public class MoviesDoInBackgroundParameter {
    
    String key;
    
    BUILD_URL_TYPE sortBy;
    
    public String getKey() {
        return key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public BUILD_URL_TYPE getSortBy() {
        return sortBy;
    }
    
    public void setSortBy(final BUILD_URL_TYPE sortBy) {
        this.sortBy = sortBy;
    }
}
