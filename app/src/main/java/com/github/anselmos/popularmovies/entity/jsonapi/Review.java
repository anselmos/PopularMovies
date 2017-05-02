package com.github.anselmos.popularmovies.entity.jsonapi;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anselmos on 29.04.17.
 */
public class Review implements Parcelable {
    
     public String id;
     public String author;
     public String content;
     public String url;
    
     @Override
     public int describeContents() { return 0; }
     
     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(this.id);
          dest.writeString(this.author);
          dest.writeString(this.content);
          dest.writeString(this.url);
     }
     
     public Review() {}
     public Review(JSONObject in) {
         try{
             this.id = in.getString("id");
             this.author = in.getString("author");
             this.content = in.getString("content");
             this.url = in.getString("url");
         }catch(JSONException ex){}
     }
     
     protected Review(Parcel in) {
          this.id = in.readString();
          this.author = in.readString();
          this.content = in.readString();
          this.url = in.readString();
     }
     
     public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
          @Override
          public Review createFromParcel(Parcel source) {return new Review(source);}
          
          @Override
          public Review[] newArray(int size) {return new Review[size];}
     };
}
