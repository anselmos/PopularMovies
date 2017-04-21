package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.updateMovies();
    }
    
    public String getApiKey() {
        return getString(R.string.api_key);
    }
    
    public void updateMovies() {
        AsyncTask<String, Integer, ArrayList<PopularEntity>> task = new DownloadMoviesAsyncTask().execute(this.getApiKey());
        ArrayList<PopularEntity> movies = null;
        try {
            movies = (ArrayList<PopularEntity>) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        
        GridView gridview = (GridView) this.findViewById(R.id.poster_grid);
        final MoviesGridViewAdapter moviesAdapter = new MoviesGridViewAdapter(this, movies);
        gridview.setAdapter(moviesAdapter);
        
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                
                PopularEntity popularEntity = (PopularEntity) moviesAdapter.getItem(position);
                Intent detailsIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                detailsIntent.putExtra("original_title", popularEntity.getOriginal_title());
                detailsIntent.putExtra("image_thumbnail", popularEntity.getPoster_path());
                detailsIntent.putExtra("plot_synopsis_overview", popularEntity.getOverview());
                detailsIntent.putExtra("vote_average", String.valueOf(popularEntity.getVote_average()));
                detailsIntent.putExtra("release_date", popularEntity.getRelease_date());
                startActivity(detailsIntent);
            }
        });
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this.getApplicationContext(), "Hello replace this with reloading images to by toprated/top...",Toast.LENGTH_LONG ).show();
                return true;
        }
        return true;
        }
    }
