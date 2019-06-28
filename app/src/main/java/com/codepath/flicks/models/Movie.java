/**
 * Katie Mishra
 * Facebook University - Android Track - 2019
 * Movie.java allows users to load movies as JSON objects.
 */

package com.codepath.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Movie {

    //values from API
    String title;
    String overview;
    String posterPath; //only the path, not full URL
    String backdropPath;
    Double voteAverage;

    // zero argument, empty constructor to implement Parceler
    public Movie() { }

    //constructor to initialize directly from JSON object
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
    }

    //to automatically generate getters, right click and press on Generate
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}
