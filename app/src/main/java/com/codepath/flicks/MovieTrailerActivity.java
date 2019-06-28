package com.codepath.flicks;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.ButterKnife;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    /*
    //resolve the player view from the layout
    @BindView(R.id.player) YouTubePlayerView playerView;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);
        ButterKnife.bind(this);

        // temporary test video id -- TODO replace with movie trailer video id
        final String videoId = "tKodtNFpzBA";

        //resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        //initialize with API key stored in secrets.xml
        playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }

    // get list of currently playing movies from API
   /*private void getNowPlaying() {
        //create the url
        String url = API_BASE_URL + "/movie/now_playing";
        //reset the request parameters
        RequestParams params = new RequestParams();
        //R accesses secrets.xml to get string
        params.put(API_KEY_PARAM, getString(R.string.api_key)); //API Key is always required
        //execute a GET request expecti ng a JSON object response
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //load the results into movie list
                try {
                    JSONArray results = response.getJSONArray("results");
                    //iterate through json array and add each movie object to the list
                    for (int i = 0; i < results.length(); i++) {
                        Movie movie = new Movie(results.getJSONObject(i));
                        movies.add(movie);
                        //notify adapter that a row was added
                        adapter.notifyItemInserted(movies.size() - 1);
                    }
                    //% does concatenation
                    Log.i("TAG", String.format("Loaded %s movies", results.length()));

                } catch (JSONException e) {
                    Log.e("TAG", "failed to parse now playing movie");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TAG", "failed to get data from now playing endpoint");
            }
        });
    }*/
}
