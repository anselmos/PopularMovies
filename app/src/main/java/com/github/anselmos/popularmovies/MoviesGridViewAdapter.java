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
        return position;
    }
    
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.activity_main, null);
        }
        ImageView imageView = new ImageView(this.mContext);
        final PopularEntity popularEntity = this.list.get(position);
        insertImageInView(this.mContext, imageView, popularEntity.getPoster_path());
        return imageView;
    }
    
    public static void insertImageInView(final Context context, final ImageView imageView, final String imagePath) {
        String url = new PosterUrlBuilder().getSmallImage(imagePath);
        Picasso.with(context).load(url).into(imageView);
    }
}
