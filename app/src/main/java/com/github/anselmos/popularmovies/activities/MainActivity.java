package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.adapters.MoviesGridViewAdapter;
import com.github.anselmos.popularmovies.async.DownloadMoviesAsyncTask;
import com.github.anselmos.popularmovies.models.enums.BUILD_URL_TYPE;
import com.github.anselmos.popularmovies.models.PopularEntity;
import com.github.anselmos.popularmovies.utils.MoviesDoInBackgroundParameter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    
    //TODO https://developer.android.com/guide/components/activities/activity-lifecycle.html
    // TODO https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true#h.7sxo8jefdfll
    // TODO https://classroom.udacity.com/nanodegrees/nd818/parts/18ccf60f-3c44-46a8-8e73-5a7798c905d3/modules/3124c543-548a-4e70-a0d2-047c9e5af785/lessons/4324689102239847/concepts/42741746260923
    
    ArrayList<PopularEntity> movies = null;
    
    MoviesGridViewAdapter adapter = null;
    
    @BindView(R.id.poster_grid)
    GridView gridView;
    
    @BindView(R.id.refreshButton)
    Button refreshButton;
    
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        if (!isNetworkConnected()) {
            showNoInternetAccess();
        } else {
            this.refresh();
        }
    }
    
    @OnClick(R.id.refreshButton)
    public void onRefreshButtonClick(){
        if (isNetworkConnected()) {
            refresh();
        }
    }
    
    public void refresh() {
        progressBar.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);
        this.createAdapter(this.getApplicationContext(), movies);
        this.updateMovies();
    }
    
    public void showNoInternetAccess() {
        progressBar.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
    }
    
    public String getApiKey() {
        return getString(R.string.api_key);
    }
    
    public void updateMovies() {
        /**
         * By default makes order by MOST POPULAR.
         */
        downloadMoviesList(BUILD_URL_TYPE.MOST_POPULAR);
        
        this.adapter = createAdapter(this.getApplicationContext(), this.movies);
        gridView.setAdapter(this.adapter);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                
                PopularEntity popularEntity = (PopularEntity) adapter.getItem(position);
                Intent detailsIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                detailsIntent.putExtra("parcelable", popularEntity);
                detailsIntent.putExtra("apiKey", getApiKey());
                startActivity(detailsIntent);
            }
        });
    }
    
    private void downloadMoviesList(BUILD_URL_TYPE sortBy) {
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
        if (!isNetworkConnected()) {
            Toast.makeText(getApplicationContext(), "Connect your device to internet", Toast.LENGTH_LONG).show();
        } else {
            this.refresh();
            
            switch (item.getItemId()) {
                case R.id.most_popular:
                    this.movies.clear();
                    this.downloadMoviesList(BUILD_URL_TYPE.MOST_POPULAR);
                    this.adapter.refreshEvents(this.movies);
                    break;
                
                case R.id.top_rated:
                    this.movies.clear();
                    this.downloadMoviesList(BUILD_URL_TYPE.TOP_RATED);
                    this.adapter.refreshEvents(this.movies);
                    break;
                case R.id.user_favourites:
                    Realm realm = Realm.getDefaultInstance();
                    Toast.makeText(getApplicationContext(), String.valueOf(realm.where(PopularEntity.class).count()), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        
        return true;
    }
    
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        
        return cm.getActiveNetworkInfo() != null;
    }
}
