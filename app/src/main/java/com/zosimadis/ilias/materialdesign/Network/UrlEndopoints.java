package com.zosimadis.ilias.materialdesign.Network;

/**
 * Created by ilias on 18/8/2015.
 */
public class UrlEndopoints {

    public static final String API_KEY = "a24511b30c51d892aa690f2177a755ed";
    public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w500";
    public static final String URL_MOVIEDB_SEARCH = "http://api.themoviedb.org/3/search/movie";
    public static final String URL_MOVIEDB_UPCOMING = "http://api.themoviedb.org/3/movie/upcoming";
    public static final String URL_CHAR_PARAM = "?api_key=";
    public static final String URL_CHAR_QUERY = "&query=";
    public static final String URL_CHAR_PAGE = "&page=";

    public static String getRequest(int limit) {
        return UrlEndopoints.URL_MOVIEDB_UPCOMING + UrlEndopoints.URL_CHAR_PARAM + UrlEndopoints.API_KEY + UrlEndopoints.URL_CHAR_PAGE + limit;
    }

}
