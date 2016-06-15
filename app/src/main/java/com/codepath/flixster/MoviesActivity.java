package com.codepath.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MoviesAdapter moviesAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // 1. get the actual movies

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movies);
        lvItems.setAdapter(moviesAdapter);


        String url =  "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results"); // assign the stuff you get to the key results
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    moviesAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", movieJsonResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });

        // 2. get the listview that we want to populate
        //ListView lvMovies = (ListView) findViewById(R.id.lvMovies);

        // 3. create an array adapter
        // adapter takes the data and maps it to the views
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies); // context is activity ("this" is this activity)
        // arguments: context, what is the view to put in the listview, data
       // MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. associate the array adapter with the listview
        //if (lvMovies != null){
         //   lvMovies.setAdapter(adapter);
        //}
    }
}
