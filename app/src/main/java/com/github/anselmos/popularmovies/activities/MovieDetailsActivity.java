package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.entity.enums.ImageSize;
import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.ApiAccess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
        System.out.println("entity!" + entity.original_title);
        this.updateMovieDetails(entity);
    }
    
    public void updateMovieDetails(PopularEntity entity) {
        this.original_title.setText(entity.original_title);
        this.plot_synopsis_overview.setText(entity.overview);
        this.vote_average.setText(String.valueOf(entity.vote_average));
        this.release_date.setText(entity.release_date);
        
        ApiAccess.insertImageInView(this.getApplicationContext(), imageView, entity.poster_path, ImageSize.MEDIUM);
    }
}
