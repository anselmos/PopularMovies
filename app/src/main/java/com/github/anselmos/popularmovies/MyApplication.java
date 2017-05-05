package com.github.anselmos.popularmovies;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by anselmos on 04.05.17.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        
    }
}
