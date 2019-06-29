/**
 * Katie Mishra
 * Facebook University - Android Track - 2019
 * Wraps a list of movies
 */

package com.codepath.flicks;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flicks.models.Config;
import com.codepath.flicks.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Katie Mishra - Facebook University 2019 - krmishra@stanford.edu
 * MovieAdapter provides information for the RecyclerView including an image,
 * title, and overview. The image changes based on portrait or landscape mode.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // list of movies
    ArrayList<Movie> movies;
    //config needed for image urls
    Config config;
    // context for rendering
    Context context;
    @BindView(R.id.tvTitle) TextView tvTitle;

    // creates and inflates a new view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create the view using the item_movie layout, reference to parent and whether or not the view should be attached to the root
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        //return a new ViewHolder
        return new ViewHolder(movieView);
    }

    // binds an inflated view to a new item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the movie data at the specified position
        Movie movie = movies.get(position);
        // populate the view with the movie data
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        // determine the current orientation
        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        // build url for poster image
        String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());

        //if in portrait mode, load the poster image
        if (isPortrait) {
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        } else {
            // load the backdrop image
            imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        }

        // get the correct placeholder and imageview for the current orientation
        int placeholderId = isPortrait ? R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;
        ImageView imageView = isPortrait ? holder.ivPosterImage : holder.ivBackdropImage;

        //load image using glide
        GlideApp.with(context)
                .load(imageUrl)
                // round corners
//                .transform(new RoundedCornersTransformation(context, 25, 0))
                // placeholder while images are loading
                .placeholder(placeholderId)
                // could use a different image to indicate that download failed
                .error(R.drawable.flicks_movie_placeholder)
                .into(imageView);
    }

    // returns the total number of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // initalize with list (generate trick)
    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    //create the viewholder as a static inner class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //track view objects
        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivBackdropImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //lookupview objects by id
            ivPosterImage = (ImageView) itemView.findViewById(R.id.ivPosterImage);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivBackdropImage = (ImageView) itemView.findViewById(R.id.ivBackdropImage);
            //add itemView's OnClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // get item position
            int position = getAdapterPosition();
            // make sure the position exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                //serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                // show the activity
                context.startActivity(intent);
            }
        }
    }
}
