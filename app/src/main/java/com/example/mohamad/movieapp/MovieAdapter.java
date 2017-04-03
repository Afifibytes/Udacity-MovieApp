package com.example.mohamad.movieapp;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends BaseAdapter {

    private List<Movie> mMovies;

    private Context mContext;


    public MovieAdapter() {
    }

    //MovieAdapter constructor1
    public MovieAdapter(Context context) {
        this.mMovies = new ArrayList<>();
        this.mContext = context;
    }


    //MovieAdapter constructor2
    public MovieAdapter(List<Movie> movies ,Context context) {
        this.mMovies = movies;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }


    @Override
    // should return the actual object at the specified position in the adapter
    public Movie getItem(int i) {
        return mMovies.get(i);
    }

    @Override
    //should return the row id of the item
    public long getItemId(int i) {
        return mMovies.get(i).getId();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View view1 = view;
        if (view1 == null){

            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view1 = inflater.inflate(R.layout.movie_item, null);
            ImageView MoviePoster = (ImageView) view1.findViewById(R.id.movie_poster);

            Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500"+mMovies.get(position).getPosterPath()).into(MoviePoster);
        }

        return view1;
    }

}
