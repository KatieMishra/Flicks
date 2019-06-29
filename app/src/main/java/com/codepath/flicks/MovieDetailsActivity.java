package com.codepath.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flicks.models.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Katie Mishra - Facebook University 2019 - krmishra@stanford.edu
 * MovieDetailsActivity controls a screen which displays additional information
 * about movies including rating and full description.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    //movie to display
    Movie movie;
    //movie image
    ImageView movieImage;
    //String videoId;
    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    // the adapter wired to the recycler view
    MovieAdapter adapter;

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        //unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for %s", movie.getTitle()));

        //set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }

    //get the configuration from the API
    /*private void getConfiguration() {
        //create the url
        String url = API_BASE_URL + "/configuration";
        //reset the request parameters
        RequestParams params = new RequestParams();
        //R accesses secrets.xml to get string
        params.put(API_KEY_PARAM, getString(R.string.api_key)); //API Key is always required
        //execute a GET request expecti ng a JSON object response
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    config = new Config(response);
                    Log.i(TAG, String.format("Loaded configuration with imageBaseUrl %s and posterSize %s",
                            config.getImageBaseUrl(),
                            config.getPosterSize()));
                    //pass config object to adapter
                    adapter.setConfig(config);

                    // get the now playing movie list
                    //call get now playing here so that it can only execute once configuration has successfully completed
                    getNowPlaying();
                } catch (JSONException e) {
                    logError("Failed parsing configuration", e, true);
                }

            }

            //if failed getting data, log error
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed getting configuration", throwable, true);
            }

        });
    }*/
}


