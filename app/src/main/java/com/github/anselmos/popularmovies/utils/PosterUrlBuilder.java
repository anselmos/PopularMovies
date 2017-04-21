package com.github.anselmos.popularmovies.utils;

import com.github.anselmos.popularmovies.entity.enums.ImageSize;

/**
 * Created by anselmos on 07.04.17.
 */
public class PosterUrlBuilder {
    public static String API = "https://image.tmdb.org/t/p/";

    
    public String build(final String imageSize, final String imagePath){
        return API + "w"+ imageSize + imagePath;
    }
    
    public String getSmallImage(final String imagePath){
        return this.build(String.valueOf(ImageSize.SMALL.value), imagePath);
    }
    
    public String getMediumImage(final String imagePath){
        return this.build(String.valueOf(ImageSize.MEDIUM.value), imagePath);
    }
    public String getLargeImage(final String imagePath){
        return this.build(String.valueOf(ImageSize.LARGE.value), imagePath);
    }
}
