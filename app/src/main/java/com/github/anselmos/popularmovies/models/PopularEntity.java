package com.github.anselmos.popularmovies.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PopularEntity  extends RealmObject implements Parcelable{
    
    public String poster_path;
    
    public String adult;
    
    public String overview;
    
    public String release_date;
    
    public String genre_ids;
    
    @PrimaryKey
    public int id;
    
    public String original_title;
    
    public String original_language;
    
    public String title;
    
    public String backdrop_path;
    
    public float popularity;
    
    public int vote_count;
    
    public boolean video;
    
    public double vote_average;
    //Additional user vote not included in API!
    public float user_vote = 0;
    
    public float getUser_vote() {
        return user_vote;
    }
    
    public void setUser_vote(final float user_vote) {
        this.user_vote = user_vote;
    }
    
    
    @Override
    public int describeContents() { return 0; }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeString(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.genre_ids);
        dest.writeInt(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeFloat(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.vote_average);
    }
    
    public PopularEntity() {}
    
    public PopularEntity(JSONObject object){
        try {
            this.original_title = (object.get("original_title").toString());
            this.poster_path = (object.get("poster_path").toString());
            this.adult = (object.get("adult").toString());
            this.overview = (object.get("overview").toString());
            this.release_date = (object.get("release_date").toString());
            this.vote_average = (object.getDouble("vote_average"));
            this.id = object.getInt("id");
            
        }catch(JSONException ex){}
    }
    
    protected PopularEntity(Parcel in) {
        this.poster_path = in.readString();
        this.adult = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.genre_ids = in.readString();
        this.id = in.readInt();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readFloat();
        this.vote_count = in.readInt();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
    }
    
    public static final Parcelable.Creator<PopularEntity> CREATOR = new Parcelable.Creator<PopularEntity>() {
        @Override
        public PopularEntity createFromParcel(Parcel source) {return new PopularEntity(source);}
        
        @Override
        public PopularEntity[] newArray(int size) {return new PopularEntity[size];}
    };
    
}