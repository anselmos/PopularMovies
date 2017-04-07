package com.github.anselmos.popularmovies.entity.db;

/**
 * Created by anselmos on 07.04.17.
 */
public class Movie {
    
    protected int id;
    
    protected int TMDBId; // The Movie DataBase ID
    
    protected String title;
    
    protected String image;
    
    protected String synopsis;
    
    protected boolean isFavourite = false;
    
    protected double rating;
    
    public int getId() {
        return id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getTMDBId() {
        return TMDBId;
    }
    
    public void setTMDBId(final int TMDBId) {
        this.TMDBId = TMDBId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(final String image) {
        this.image = image;
    }
    
    public String getSynopsis() {
        return synopsis;
    }
    
    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }
    
    public boolean isFavourite() {
        return isFavourite;
    }
    
    public void setFavourite(final boolean favourite) {
        isFavourite = favourite;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(final double rating) {
        this.rating = rating;
    }
}
