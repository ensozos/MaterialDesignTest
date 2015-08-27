package com.zosimadis.ilias.materialdesign.Fragments;


import android.os.Bundle;
import android.os.Parcelable;
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
import com.zosimadis.ilias.materialdesign.Activities.MyApplication;
import com.zosimadis.ilias.materialdesign.Log.L;
import com.zosimadis.ilias.materialdesign.Network.Keys;
import com.zosimadis.ilias.materialdesign.Network.UrlEndopoints;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;
import com.zosimadis.ilias.materialdesign.R;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUpcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpcoming extends Fragment implements UpcomingLoadedListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String STATE_MOVIE = "state_movie";
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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_MOVIE, (ArrayList<? extends Parcelable>) resultMovies);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        listUpcomingMovies = (RecyclerView) view.findViewById(R.id.listUpcoming);
        listUpcomingMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterUpcoming = new AdapterUpcoming(getActivity());
        listUpcomingMovies.setAdapter(adapterUpcoming);
        if (savedInstanceState != null) {
            resultMovies = savedInstanceState.getParcelableArrayList(STATE_MOVIE);

        } else {
            resultMovies = MyApplication.getWritableDatabase().readMovies();
            if(resultMovies.isEmpty()){
                new UpcomingTask(this).execute();
            }
        }
        adapterUpcoming.setListMovies(resultMovies);
        return view;
    }


    @Override
    public void onUpcomingLoaded(List<Movie> movieList) {
        adapterUpcoming.setListMovies(movieList);
    }
}
