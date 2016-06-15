package com.codepath.flixster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // here's access to the data from before. here's some data, match up the data and view and return the view

        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        } // inflates that xml. creates a representation in memory so we can access it
        // find the textView inside the convertView wrapper

        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);

        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        Log.d("MoviesAdapter", "Position: " + position);

        String imageUri = movie.getPosterUrl();
        Picasso.with(getContext()).load(imageUri).into(ivPoster);
        // rounded corners
        //Picasso.with(getContext()).load(imageUri)
        //        .transform(new RoundedCornersTransformation(100, 100)).into((ImageView) convertView.findViewById(R.id.ivPoster));


        // Return the completed view to render on screen
        return convertView;

    }
}
