package com.github.anselmos.popularmovies.entity.jsonapi;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anselmos on 07.04.17.
 */
// TODO - check http://stackoverflow.com/questions/13786997/android-passing-object-between-activities-and-make-it-parcelable
//TODO - check http://www.developerphil.com/parcelable-vs-serializable/
    
public class PopularEntity implements Parcelable {
    
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
    public double vote_average;
    
    public PopularEntity(JSONObject object){
        try {
            this.setOriginal_title(object.get("original_title").toString());
            this.setPoster_path(object.get("poster_path").toString());
            this.setAdult(object.get("adult").toString());
            this.setOverview(object.get("overview").toString());
            this.setRelease_date(object.get("release_date").toString());
            this.setVote_average(object.getDouble("vote_average"));
        }catch(JSONException ex){}
    }
    
    protected PopularEntity(Parcel in) {
        poster_path = in.readString();
        adult = in.readString();
        overview = in.readString();
        release_date = in.readString();
        genre_ids = in.readString();
        id = in.readInt();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readFloat();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
    }
    
    public static final Creator<PopularEntity> CREATOR = new Creator<PopularEntity>() {
        @Override
        public PopularEntity createFromParcel(Parcel in) {
            return new PopularEntity(in);
        }
        
        @Override
        public PopularEntity[] newArray(int size) {
            return new PopularEntity[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.getOriginal_title());
        dest.writeString(this.getPoster_path());
    }
    
    public String toString(){
        return this.original_title + " , "+ this.release_date;
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
    
    public double getVote_average() {
        return vote_average;
    }
    
    public void setVote_average(final double vote_average) {
        this.vote_average = vote_average;
    }
    
}
