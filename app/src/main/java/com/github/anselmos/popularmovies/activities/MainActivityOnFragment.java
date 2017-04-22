package com.github.anselmos.popularmovies.activities;

import com.github.anselmos.popularmovies.R;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by anselmos on 22.04.17.
 */
public class MainActivityOnFragment extends FragmentActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
    }
}
