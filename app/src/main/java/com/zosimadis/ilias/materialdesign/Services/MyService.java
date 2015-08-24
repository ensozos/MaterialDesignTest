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
public class MyService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        L.t(getApplicationContext(), "onStartJob");
        new MyTask(this).execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private static class MyTask extends AsyncTask<JobParameters, Void, JobParameters> implements Keys.UpcomingEndpoints {

        private VolleySingleton volleySingleton;
        private RequestQueue requestQueue;
        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MyService myService;

        MyTask(MyService myService) {
            this.myService = myService;
            volleySingleton = VolleySingleton.getInstance();
            requestQueue = volleySingleton.getRequestQueue();
        }

        private JSONObject sendJsonRequest() {

            JSONObject response = null;
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    getRequest(3),
                    (String) null,
                    requestFuture, requestFuture
            );
            requestQueue.add(jsonObjectRequest);
            try {
                response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            return response;
        }

        private List<Movie> parseJSONResponse(JSONObject response) {

            List<Movie> movieList = new ArrayList<>();

            if (response != null && response.length() != 0) {

                try {

                    JSONArray results = null;
                    results = response.getJSONArray(KEY_RESULTS);
                    for (int i = 0; i < results.length(); i++) {
                        Movie movie = new Movie();
                        JSONObject currentMovie = results.getJSONObject(i);
                        Long id = currentMovie.getLong(KEY_ID);
                        String title = currentMovie.getString(KEY_TITLE);
                        String overview = currentMovie.getString(KEY_OVERVIEW);
                        int popularity = currentMovie.getInt(KEY_POPULARITY);
                        int vote = currentMovie.getInt(KEY_VOTE);
                        String language = currentMovie.getString(KEY_LANG);
                        String dateString = currentMovie.getString(KEY_RELEASE);
                        Date date = dateFormat.parse(dateString);
                        String poster = currentMovie.getString(KEY_POSTER);


                        movie.setId(id);
                        movie.setTitle(title);
                        movie.setLanguage(language);
                        movie.setOverview(overview);
                        movie.setVote(vote);
                        movie.setPopularity(popularity);
                        movie.setUrlImage(poster);
                        movie.setReleaseDate(date);
                        movieList.add(movie);
                    }


                } catch (JSONException e) {
                    L.m("UpCommign", "JSON ERROR!");
                } catch (ParseException e) {
                    L.m("UpComming", "Bad Date Format!");
                }

            }
            return movieList;

        }

        public static String getRequest(int limit) {
            return UrlEndopoints.URL_MOVIEDB_UPCOMING + UrlEndopoints.URL_CHAR_PARAM + UrlEndopoints.API_KEY + UrlEndopoints.URL_CHAR_PAGE + limit;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JobParameters doInBackground(JobParameters... jobParameterses) {

            JSONObject response = sendJsonRequest();
            List<Movie> results = parseJSONResponse(response);
            MyApplication.getWritableDatabase().insertMovies(results,true);
            return jobParameterses[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            myService.jobFinished(jobParameters, false);

        }
    }


}
