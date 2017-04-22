package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.asynctasks.DownloadMoviesAsyncTask;
import com.github.anselmos.popularmovies.adapters.MoviesGridViewAdapter;
import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.MoviesDoInBackgroundParameter;
import com.github.anselmos.popularmovies.utils.UrlBuilder;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//TODO move to fragments!

//TODO add endless scrolling

//TODO add more then one element per line
public class MainActivity extends AppCompatActivity {
    
    ArrayList<PopularEntity> movies = null;
    
    MoviesGridViewAdapter adapter = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.createAdapter(this.getApplicationContext(), movies);
        this.updateMovies();
    }
    
    public String getApiKey() {
        return getString(R.string.api_key);
    }
    
    public void updateMovies() {
        /**
         * By default makes order by MOST POPULAR.
         */
        downloadMoviesList(UrlBuilder.SORT_BY.MOST_POPULAR);
        
        GridView gridview = (GridView) this.findViewById(R.id.poster_grid);
        this.adapter = createAdapter(this.getApplicationContext(), this.movies);
        gridview.setAdapter(this.adapter);
        
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                
                PopularEntity popularEntity = (PopularEntity) adapter.getItem(position);
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
    
    private void downloadMoviesList(UrlBuilder.SORT_BY sortBy) {
        MoviesDoInBackgroundParameter param = new MoviesDoInBackgroundParameter();
        param.setKey(this.getApiKey());
        param.setSortBy(sortBy);
        AsyncTask<MoviesDoInBackgroundParameter, Integer, ArrayList<PopularEntity>> task = new DownloadMoviesAsyncTask().execute(param);
        try {
            this.movies = ((ArrayList<PopularEntity>) task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    
    public MoviesGridViewAdapter createAdapter(Context context, ArrayList<PopularEntity> movies) {
        return new MoviesGridViewAdapter(context, movies);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.most_popular:
                this.downloadMoviesList(UrlBuilder.SORT_BY.MOST_POPULAR);
                this.adapter.refreshEvents(this.movies);
                return true;
            
            case R.id.top_rated:
                this.downloadMoviesList(UrlBuilder.SORT_BY.TOP_RATED);
                this.adapter.refreshEvents(this.movies);
                return true;
        }
        return true;
    }
}