package com.github.anselmos.popularmovies.adapters;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.models.enums.ImageSize;
import com.github.anselmos.popularmovies.models.PopularEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.github.anselmos.popularmovies.utils.ApiAccess.insertImageInView;

/**
 * Created by anselmos on 08.04.17.
 */
public class MoviesGridViewAdapter extends BaseAdapter {
    
    private final Context mContext;
    
    private final ArrayList<PopularEntity> list;
    
    public MoviesGridViewAdapter(Context context, ArrayList<PopularEntity> list) {
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
        insertImageInView(this.mContext, imageView, popularEntity.poster_path, ImageSize.SMALL);
        return imageView;
    }
    
    public void refreshEvents(List<PopularEntity> movies) {
        this.list.clear();
        this.list.addAll(movies);
        notifyDataSetChanged();
    }
    

}
