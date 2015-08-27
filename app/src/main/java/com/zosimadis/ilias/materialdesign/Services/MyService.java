package com.zosimadis.ilias.materialdesign.Services;


import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.zosimadis.ilias.materialdesign.Activities.MyApplication;
import com.zosimadis.ilias.materialdesign.Log.L;
import com.zosimadis.ilias.materialdesign.Network.Keys;
import com.zosimadis.ilias.materialdesign.Network.UrlEndopoints;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;
import com.zosimadis.ilias.materialdesign.Task.UpcomingTask;
import com.zosimadis.ilias.materialdesign.callbacks.UpcomingLoadedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by ilias on 23/8/2015.
 */
public class MyService extends JobService implements UpcomingLoadedListener {

   private JobParameters jobPara;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        this.jobPara = jobParameters;
        L.t(getApplicationContext(), "onStartJob");
        new UpcomingTask(this).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    @Override
    public void onUpcomingLoaded(List<Movie> movieList) {
        jobFinished(jobPara,false);
    }
}
