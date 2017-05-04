package com.github.anselmos.popularmovies.adapters;

import com.github.anselmos.popularmovies.R;
import com.github.anselmos.popularmovies.entity.jsonapi.Trailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anselmos on 08.04.17.
 */
public class TrailersListViewAdapter extends BaseAdapter {
    
    private final Context mContext;
    
    private final ArrayList<Trailer> list;
    
    public TrailersListViewAdapter(Context context, ArrayList<Trailer> list) {
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
        TextView textView = new TextView(this.mContext);
        final Trailer trailer = this.list.get(position);
        textView.setText(trailer.name);
        return textView;
    }
    
    public void refreshEvents(List<Trailer> trailers) {
        this.list.clear();
        this.list.addAll(trailers);
        notifyDataSetChanged();
    }
}
