package com.zosimadis.ilias.materialdesign.Network;

import com.android.volley.RequestQueue;
import com.zosimadis.ilias.materialdesign.Activities.MyApplication;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by ilias on 27/8/2015.
 */
public class Utils {
    public static List<Movie> loadMovies(RequestQueue requestQueue){
        JSONObject response = Requestor.sendJsonRequest(requestQueue,UrlEndopoints.getRequest(1));
        List<Movie> movieList = Parser.parseJSONResponse(response);
        MyApplication.getWritableDatabase().insertMovies(movieList,true);
        return movieList;
    }
}
