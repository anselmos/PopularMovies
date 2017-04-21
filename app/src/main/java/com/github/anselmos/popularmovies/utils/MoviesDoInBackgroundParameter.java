package com.github.anselmos.popularmovies.utils;

/**
 * Created by anselmos on 21.04.17.
 */
public class MoviesDoInBackgroundParameter {
    String key;
    UrlBuilder.SORT_BY sortBy;
    
    public String getKey() {
        return key;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public UrlBuilder.SORT_BY getSortBy() {
        return sortBy;
    }
    
    public void setSortBy(final UrlBuilder.SORT_BY sortBy) {
        this.sortBy = sortBy;
    }
}
