package com.github.anselmos.popularmovies.entity.jsonapi;

/**
 * Created by anselmos on 07.04.17.
 */
public class PopularEntity {
    
    public String poster_path;
    public String adult;
    public String overview;
    public String release_date;
    public String genre_ids;
    public int id;
    public String original_title;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public boolean video;
    public int vote_average;
    
    public PopularEntity(String original_title, String poster_path){
        this.setOriginal_title(original_title);
        this.setPoster_path(poster_path);
    }
    
    public String getPoster_path() {
        return poster_path;
    }
    
    public void setPoster_path(final String poster_path) {
        this.poster_path = poster_path;
    }
    
    public String getAdult() {
        return adult;
    }
    
    public void setAdult(final String adult) {
        this.adult = adult;
    }
    
    public String getOverview() {
        return overview;
    }
    
    public void setOverview(final String overview) {
        this.overview = overview;
    }
    
    public String getRelease_date() {
        return release_date;
    }
    
    public void setRelease_date(final String release_date) {
        this.release_date = release_date;
    }
    
    public String getGenre_ids() {
        return genre_ids;
    }
    
    public void setGenre_ids(final String genre_ids) {
        this.genre_ids = genre_ids;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getOriginal_title() {
        return original_title;
    }
    
    public void setOriginal_title(final String original_title) {
        this.original_title = original_title;
    }
    
    public String getOriginal_language() {
        return original_language;
    }
    
    public void setOriginal_language(final String original_language) {
        this.original_language = original_language;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getBackdrop_path() {
        return backdrop_path;
    }
    
    public void setBackdrop_path(final String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    
    public float getPopularity() {
        return popularity;
    }
    
    public void setPopularity(final float popularity) {
        this.popularity = popularity;
    }
    
    public int getVote_count() {
        return vote_count;
    }
    
    public void setVote_count(final int vote_count) {
        this.vote_count = vote_count;
    }
    
    public boolean isVideo() {
        return video;
    }
    
    public void setVideo(final boolean video) {
        this.video = video;
    }
    
    public int getVote_average() {
        return vote_average;
    }
    
    public void setVote_average(final int vote_average) {
        this.vote_average = vote_average;
    }
}
