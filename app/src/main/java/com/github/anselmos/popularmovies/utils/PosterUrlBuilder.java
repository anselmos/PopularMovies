package com.github.anselmos.popularmovies.utils;

/**
 * Created by anselmos on 07.04.17.
 */
public class PosterUrlBuilder {
    public static String API = "https://image.tmdb.org/t/p/";
    public enum ImageSize{
        SMALL(154), MEDIUM(342), LARGE(780);
    
        private int value;
        private ImageSize(int value){
            this.value = value;
        }
    }
    
    public String build(final String imageSize, final String imagePath){
        return API + "w"+ imageSize + imagePath;
    }
    
    public String getSmallImage(final String imagePath){
        return this.build(String.valueOf(ImageSize.SMALL.value), imagePath);
    }
    
    public String getMediumImage(final String imagePath){
        return this.build(String.valueOf(ImageSize.MEDIUM.value), imagePath);
    }
}
