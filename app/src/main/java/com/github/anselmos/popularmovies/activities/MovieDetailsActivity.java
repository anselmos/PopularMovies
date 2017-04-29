package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.async.DownloadMoviesAsyncTask;
import com.github.anselmos.popularmovies.async.FetchReviewsAsyncTask;
import com.github.anselmos.popularmovies.async.FetchTrailersAsyncTask;
import com.github.anselmos.popularmovies.entity.enums.ImageSize;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.entity.jsonapi.Review;
import com.github.anselmos.popularmovies.entity.jsonapi.Trailer;
import com.github.anselmos.popularmovies.utils.ApiAccess;
import com.github.anselmos.popularmovies.utils.MoviesDoInBackgroundParameter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {
    
    @BindView(R.id.original_title)
    TextView original_title;
    
    @BindView(R.id.plot_synopsis_overview)
    TextView plot_synopsis_overview;
    
    @BindView(R.id.image_thumbnail)
    ImageView imageView;
    
    @BindView(R.id.vote_average)
    TextView vote_average;
    
    @BindView(R.id.release_date)
    TextView release_date;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        ButterKnife.bind(this);
        PopularEntity entity = getIntent().getParcelableExtra("parcelable");

        String apiKey = getIntent().getStringExtra("apiKey");
        this.updateMovieDetails(entity);
        ArrayList<Trailer> trailers = null;
        ArrayList<Review> reviews = null;
        String[] param = new String[2];
        //TODO remove this LINKS
        //https://developers.themoviedb.org/3/movies/get-top-rated-movies
        //https://developers.themoviedb.org/3/movies/get-movie-videos
        param[0] = apiKey;
        
        //TODO REMOVE THIS comment when assured it's working
        //param[1] = "295693";
        param[1] = String.valueOf(entity.id);
        AsyncTask<String, Integer, ArrayList<Trailer>> trailerTask = new FetchTrailersAsyncTask().execute(param);
        try {
            trailers = (ArrayList<Trailer>) trailerTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        
        AsyncTask<String, Integer, ArrayList<Review>> reviewTask= new FetchReviewsAsyncTask().execute(param);
        try {
            reviews = (ArrayList<Review>) reviewTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(reviews);
    }
    
    public void updateMovieDetails(PopularEntity entity) {
        this.original_title.setText(entity.original_title);
        this.plot_synopsis_overview.setText(entity.overview);
        this.vote_average.setText(String.valueOf(entity.vote_average));
        this.release_date.setText(entity.release_date);
        
        ApiAccess.insertImageInView(this.getApplicationContext(), imageView, entity.poster_path, ImageSize.MEDIUM);
        
    }
    
    
}
