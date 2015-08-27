package com.zosimadis.ilias.materialdesign.callbacks;

import com.zosimadis.ilias.materialdesign.Pojo.Movie;

import java.util.List;

/**
 * Created by ilias on 27/8/2015.
 */
public interface UpcomingLoadedListener  {
    public void onUpcomingLoaded(List<Movie> movieList);
}
