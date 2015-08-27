package com.zosimadis.ilias.materialdesign.Task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import com.zosimadis.ilias.materialdesign.Network.Utils;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;
import com.zosimadis.ilias.materialdesign.callbacks.UpcomingLoadedListener;

import java.util.List;


import me.tatarka.support.job.JobParameters;

/**
 * Created by ilias on 27/8/2015.
 */
public class UpcomingTask extends AsyncTask<Void, Void, List<Movie>> {

    private UpcomingLoadedListener upcomingLoadedListener;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public UpcomingTask(UpcomingLoadedListener upcomingLoadedListener) {
        this.upcomingLoadedListener = upcomingLoadedListener;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {

        List<Movie> listMovies = Utils.loadMovies(requestQueue);
        return listMovies;
    }

    @Override
    protected void onPostExecute(List<Movie> listMovies) {
        if (upcomingLoadedListener != null) {

            upcomingLoadedListener.onUpcomingLoaded(listMovies);

        }
    }
}
