package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.async.FetchReviewsAsyncTask;
import com.github.anselmos.popularmovies.async.FetchTrailersAsyncTask;
import com.github.anselmos.popularmovies.entity.enums.ImageSize;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.entity.jsonapi.Review;
import com.github.anselmos.popularmovies.entity.jsonapi.Trailer;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    
    @BindView(R.id.trailers_linearlayout)
    LinearLayout trailers_linear_layout;
    
    @BindView(R.id.reviews_linear_layout)
    LinearLayout reviews_linear_layout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        ButterKnife.bind(this);
        PopularEntity entity = getIntent().getParcelableExtra("parcelable");

        String apiKey = getIntent().getStringExtra("apiKey");
    
        
        this.updateMovieDetails(entity);
        ArrayList<Trailer> trailers = null;
        String[] paramTrailerTask = new String[2];
        paramTrailerTask[0] = apiKey;
        paramTrailerTask[1] = String.valueOf(entity.id);
        trailers = getTrailersList(trailers, paramTrailerTask);
        addTrailersToDetailsView(trailers, this);
        ArrayList<Review> reviews = getReviewsList(paramTrailerTask);
        for(Review review: reviews){
            TextView reviewTextView = new TextView(this);
            LinearLayout.LayoutParams layoutParams = getLayoutParams();
            reviewTextView.setLayoutParams(layoutParams);
            reviewTextView.setText(review.content);
            reviews_linear_layout.addView(reviewTextView);
        }
        
        
    }
    
    @NonNull
    private LinearLayout.LayoutParams getLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,8,8,8);
        return layoutParams;
    }
    
    private ArrayList<Trailer> getTrailersList(ArrayList<Trailer> trailers, final String[] paramTrailerTask) {
        AsyncTask<String, Integer, ArrayList<Trailer>> trailerTask = new FetchTrailersAsyncTask().execute(paramTrailerTask);
        try {
            trailers = (ArrayList<Trailer>) trailerTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return trailers;
    }
    
    private ArrayList<Review> getReviewsList(final String[] param) {
        ArrayList<Review> reviews = null;
        AsyncTask<String, Integer, ArrayList<Review>> reviewTask = new FetchReviewsAsyncTask().execute(param);
        try {
            reviews = (ArrayList<Review>) reviewTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    
    public void updateMovieDetails(PopularEntity entity) {
        this.original_title.setText(entity.original_title);
        this.plot_synopsis_overview.setText(entity.overview);
        this.vote_average.setText(String.valueOf(entity.vote_average));
        this.release_date.setText(entity.release_date);
        
        ApiAccess.insertImageInView(this.getApplicationContext(), imageView, entity.poster_path, ImageSize.MEDIUM);
        
    }
    
    public Button getTrailerButton(Trailer trailer){
        Button trailerTextView = new Button(this);
        trailerTextView.setText(trailer.name);
        trailerTextView.setLayoutParams(getLayoutParams());
        return trailerTextView;
    }
    
    public void addTrailersToDetailsView(ArrayList<Trailer> trailers, Context context){
        for(Trailer trailer: trailers){

            this.trailers_linear_layout.addView(addOnClickListenerForTrailer(getTrailerButton(trailer), trailer, context));
        }

    }
    
    public Button addOnClickListenerForTrailer(final Button trailerTextView, final Trailer trailer, final Context context){
        trailerTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if (trailer.isYoutubeSite()){
                            watchYoutubeVideo(trailer.key);
                        }
                    }
                }
        );
        return trailerTextView;
    }
    
    public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
    
}
