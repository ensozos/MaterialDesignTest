package com.zosimadis.ilias.materialdesign.Activities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.zosimadis.ilias.materialdesign.Network.UrlEndopoints;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;
import com.zosimadis.ilias.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilias on 18/8/2015.
 */
public class AdapterUpcoming extends RecyclerView.Adapter<AdapterUpcoming.ViewHolderUpComing> {

    private LayoutInflater layoutInflater;
    private List<Movie> listMovies = new ArrayList<>();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;

    public AdapterUpcoming(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setListMovies(List<Movie> listMovies) {
        this.listMovies = listMovies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderUpComing onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_row_upcoming, parent, false);
        ViewHolderUpComing viewHolder = new ViewHolderUpComing(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderUpComing holder, int position) {

        Movie currentMovie = listMovies.get(position);
        holder.movieTitle.setText(currentMovie.getTitle());
        holder.movieDate.setText(currentMovie.getReleaseDate().toString());
        holder.movieVote.setRating(currentMovie.getVote());
        final String urlPoster = currentMovie.getUrlImage();
        if (urlPoster != null) {
            imageLoader.get(UrlEndopoints.URL_IMAGE + urlPoster, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.moviePoster.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    holder.moviePoster.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher));
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    static class ViewHolderUpComing extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieTitle;
        TextView movieDate;
        RatingBar movieVote;

        public ViewHolderUpComing(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieDate = (TextView) itemView.findViewById(R.id.movieDate);
            movieVote = (RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}
