package com.zosimadis.ilias.materialdesign.Network;

import com.zosimadis.ilias.materialdesign.Log.L;
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

/**
 * Created by ilias on 27/8/2015.
 */
public class Parser  implements Keys.UpcomingEndpoints  {
    public static List<Movie> parseJSONResponse(JSONObject response) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
}
