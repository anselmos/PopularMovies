package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.ApiAccess;
import com.github.anselmos.popularmovies.utils.PosterUrlBuilder;
import com.github.anselmos.popularmovies.utils.UrlBuilder;
import com.squareup.picasso.Picasso;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.updateMovies();
    
    }
    
    public String getApiKey(){
        return getString(R.string.api_key);
    }
    
    public void updateMovies(){
        AsyncTask<String, Integer, ArrayList<PopularEntity>> task = new DownloadMoviesAsyncTask().execute(this.getApiKey());
        ArrayList<PopularEntity> movies  = null;
    
        try {
            movies = (ArrayList<PopularEntity>) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ImageView view1 = (ImageView) findViewById(R.id.image1);
        for(PopularEntity popularEntity: movies){
            String url = PosterUrlBuilder.API+"w500"+popularEntity.getPoster_path();
            System.out.println(url);
            Picasso.with(this).load(url).into(view1);
            //movies_list.append(popularEntity.getOriginal_title());
        }
        
    }
}
