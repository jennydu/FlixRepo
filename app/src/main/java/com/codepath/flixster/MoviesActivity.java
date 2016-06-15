package com.codepath.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // 1. get the actual movies
        ArrayList<Movie> movies = Movie.getFakeMovies();

        // 2. get the listview that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);

        // 3. create an array adapter
        // adapter takes the data and maps it to the views
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies); // context is activity ("this" is this activity)
        // arguments: context, what is the view to put in the listview, data
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. associate the array adapter with the listview
        if (lvMovies != null){
            lvMovies.setAdapter(adapter);
        }
    }
}
