package com.github.anselmos.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anselmos on 08.04.17.
 */
public class MovieDetailsActivity extends AppCompatActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        Bundle extras = getIntent().getExtras();
        this.updateMovieDetails(extras);
    }
    
    public void updateMovieDetails(Bundle extras){
        TextView original_title = (TextView) this.findViewById(R.id.original_title);
        original_title.setText(extras.getString("original_title"));
        System.out.println(extras.getString("release_date"));
        
        TextView plot_synopsis_overview = (TextView) this.findViewById(R.id.plot_synopsis_overview);
        plot_synopsis_overview.setText(extras.getString("plot_synopsis_overview"));
        // detailsIntent.putExtra("image_thumbnail", popularEntity.getPoster_path());
        TextView vote_average = (TextView) this.findViewById(R.id.vote_average);
        vote_average.setText(extras.getString("vote_average"));
        
        TextView release_date = (TextView) this.findViewById(R.id.release_date);
        release_date.setText(extras.getString("release_date"));
    }
}
