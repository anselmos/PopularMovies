package com.github.anselmos.popularmovies.fragments;

import com.github.anselmos.popularmovies.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anselmos on 22.04.17.
 */
public class MovieListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }
    
}
