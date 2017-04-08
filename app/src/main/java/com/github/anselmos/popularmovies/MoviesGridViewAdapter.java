package com.github.anselmos.popularmovies;

import com.github.anselmos.popularmovies.entity.jsonapi.PopularEntity;
import com.github.anselmos.popularmovies.utils.PosterUrlBuilder;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by anselmos on 08.04.17.
 */
public class MoviesGridViewAdapter extends BaseAdapter{
    
    private final Context mContext;
    private final ArrayList<PopularEntity> list;
    
    public MoviesGridViewAdapter(Context context, ArrayList<PopularEntity> list){
        this.mContext = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return this.list.size();
    }
    
    @Override
    public Object getItem(final int position) {
        return this.list.get(position);
    }
    
    @Override
    public long getItemId(final int position) {
        return 0;
    }
    
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.activity_main, null);
        }
        ImageView imageView = new ImageView(this.mContext);
        final PopularEntity popularEntity = this.list.get(position);
        String url = PosterUrlBuilder.API+"w500"+popularEntity.getPoster_path();
        //System.out.println(url);
        Picasso.with(this.mContext).load(url).into(imageView);
        return imageView;
    }
}
