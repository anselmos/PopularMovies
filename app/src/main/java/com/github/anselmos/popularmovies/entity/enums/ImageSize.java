package com.github.anselmos.popularmovies.entity.enums;

public enum ImageSize {
    /**
     * Simplest ImageSize enum for TheMovieDB API URL
     * For more reference check here: https://developers.themoviedb.org/3/configuration/get-api-configuration
     */
    SMALL(154), MEDIUM(342), LARGE(780);
    
    public int value;
    
    private ImageSize(int value) {
        this.value = value;
    }
}