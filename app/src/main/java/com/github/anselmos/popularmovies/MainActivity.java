package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        TextView movies_list = (TextView) findViewById(R.id.movies_list);
        for(PopularEntity popularEntity: movies){
            movies_list.append(popularEntity.getOriginal_title());
        }
        
    }
}
