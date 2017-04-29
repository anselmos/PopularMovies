package com.github.anselmos.popularmovies.entity.jsonapi;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anselmos on 28.04.17.
 */
public class Trailer implements Parcelable {
     public String id;
     public String iso_639_1;
     public String iso_3166_1;
     public String key;
     public String name;
     public String site;
     public int size;
     public String type;
     
     @Override
     public int describeContents() { return 0; }
     
     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(this.id);
          dest.writeString(this.iso_639_1);
          dest.writeString(this.iso_3166_1);
          dest.writeString(this.key);
          dest.writeString(this.name);
          dest.writeString(this.site);
          dest.writeInt(this.size);
          dest.writeString(this.type);
     }
     
     public Trailer() {}
    
    public Trailer(JSONObject in) {
         try {
              this.id = in.getString("id");
              this.iso_639_1 = in.getString("iso_639_1");
              this.iso_3166_1 = in.getString("iso_3166_1");
              this.key = in.getString("key");
              this.name = in.getString("name");
              this.site = in.getString("site");
              this.size = in.getInt("size");
              this.type = in.getString("type");
         }catch(JSONException ex){}
        
    }
     protected Trailer(Parcel in) {
          this.id = in.readString();
          this.iso_639_1 = in.readString();
          this.iso_3166_1 = in.readString();
          this.key = in.readString();
          this.name = in.readString();
          this.site = in.readString();
          this.size = in.readInt();
          this.type = in.readString();
     }
     
     public static final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>() {
          @Override
          public Trailer createFromParcel(Parcel source) {return new Trailer(source);}
          
          @Override
          public Trailer[] newArray(int size) {return new Trailer[size];}
     };
}
