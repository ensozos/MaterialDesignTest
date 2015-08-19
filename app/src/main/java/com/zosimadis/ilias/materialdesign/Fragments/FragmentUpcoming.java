package com.zosimadis.ilias.materialdesign.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zosimadis.ilias.materialdesign.Activities.AdapterUpcoming;
import com.zosimadis.ilias.materialdesign.Log.L;
import com.zosimadis.ilias.materialdesign.Network.Keys;
import com.zosimadis.ilias.materialdesign.Network.UrlEndopoints;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;
import com.zosimadis.ilias.materialdesign.R;

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
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpcoming extends Fragment implements Keys.UpcomingEndpoints {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<Movie> resultMovies = new ArrayList<>();
    private RecyclerView listUpcomingMovies;
    private AdapterUpcoming adapterUpcoming;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentUpcoming.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUpcoming newInstance(String param1, String param2) {
        FragmentUpcoming fragment = new FragmentUpcoming();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUpcoming() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest();
    }

    private void sendJsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                getRequest(3),
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultMovies = parseJSONResponse(response);
                        adapterUpcoming.setListMovies(resultMovies);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("UpComing",error+"");

                    }
                });
        requestQueue.add(jsonObjectRequest);
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
                L.m("UpCommign","JSON ERROR!");
            } catch (ParseException e) {
                L.m("UpComming", "Bad Date Format!");
            }

        }
        return movieList;

    }

    public static String getRequest(int limit) {
        return UrlEndopoints.URL_MOVIEDB_UPCOMING + UrlEndopoints.URL_CHAR_PARAM + UrlEndopoints.API_KEY + UrlEndopoints.URL_CHAR_PAGE+limit;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        listUpcomingMovies = (RecyclerView) view.findViewById(R.id.listUpcoming);
        listUpcomingMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterUpcoming = new AdapterUpcoming(getActivity());
        listUpcomingMovies.setAdapter(adapterUpcoming);
        sendJsonRequest();
        return view;
    }


}
