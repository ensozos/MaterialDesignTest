package com.zosimadis.ilias.materialdesign.Database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.method.MovementMethod;
import android.util.Log;

import com.zosimadis.ilias.materialdesign.Log.L;
import com.zosimadis.ilias.materialdesign.Network.Keys;
import com.zosimadis.ilias.materialdesign.Pojo.Movie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ilias on 23/8/2015.
 */
public class DBMovies {
    public static final int UP_COMMING = 0;
    private MoviesHelper moviesHelper;
    private SQLiteDatabase mDatabase;

    public DBMovies(Context context) {
        moviesHelper = new MoviesHelper(context);
        mDatabase = moviesHelper.getWritableDatabase();
    }

    public void insertMovies(List<Movie> listMovies, boolean clearPrevious) {

        if(clearPrevious){
            deleteMovies();
        }

        //create a sql prepared statement
        String sql = " INSERT INTO " + MoviesHelper.TABLE_UP_COMMING + " VALUES (?,?,?,?,?,?,?,?);";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        for (int i = 0; i < listMovies.size(); i++) {
            Movie currentMovie = listMovies.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            statement.bindLong(2, currentMovie.getId());
            statement.bindString(3, currentMovie.getTitle());
            statement.bindLong(4, currentMovie.getReleaseDate() == null ? -1 : currentMovie.getReleaseDate().getTime());
            statement.bindDouble(5, currentMovie.getVote());
            statement.bindString(5, currentMovie.getOverview());
            statement.bindString(6, currentMovie.getUrlImage());
            statement.bindDouble(8, currentMovie.getPopularity());
            statement.bindString(7, currentMovie.getLanguage());


            statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public List<Movie> readMovies() {
        List<Movie> listMovies = new ArrayList<>();

        //get a list of columns to be retrieved, we need all of them
        String[] columns = {MoviesHelper.COLUMN_UID,
                MoviesHelper.COLUMN_TITLE,
                MoviesHelper.COLUMN_RELEASE_DATE,
                MoviesHelper.COLUMN_VOTE,
                MoviesHelper.COLUMN_OVERVIEW,
                MoviesHelper.COLUMN_URL_POSTER,
                MoviesHelper.COLUMN_POPULARITY,
                MoviesHelper.COLUMN_LANG,
        };


        Cursor cursor = mDatabase.query(MoviesHelper.TABLE_UP_COMMING, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                Movie movie = new Movie();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_TITLE)));
                long releaseDateMilliseconds = cursor.getLong(cursor.getColumnIndex(MoviesHelper.COLUMN_RELEASE_DATE));
                movie.setReleaseDate(releaseDateMilliseconds != -1 ? new Date(releaseDateMilliseconds) : null);
                movie.setVote(cursor.getInt(cursor.getColumnIndex(MoviesHelper.COLUMN_VOTE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_OVERVIEW)));
                movie.setUrlImage(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_POSTER)));
                movie.setPopularity(cursor.getInt(cursor.getColumnIndex(MoviesHelper.COLUMN_POPULARITY)));
                movie.setLanguage(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_LANG)));


                //add the movie to the list of movie objects which we plan to return
                listMovies.add(movie);
            }
            while (cursor.moveToNext());
        }


        return listMovies;
    }

    public void deleteMovies() {
        mDatabase.delete( MoviesHelper.TABLE_UP_COMMING , null, null);
    }

    private static class MoviesHelper extends SQLiteOpenHelper {

        public static final String TABLE_UP_COMMING = "movies";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE = "vote";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_URL_POSTER = "url_poster";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_LANG = "language";


        private static final String CREATE_TABLE_UPCOMMING = "CREATE TABLE " + TABLE_UP_COMMING + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_RELEASE_DATE + " INTEGER," +
                COLUMN_VOTE + " INTEGER," +
                COLUMN_OVERVIEW + " TEXT," +
                COLUMN_URL_POSTER + " TEXT," +
                COLUMN_POPULARITY + " INTEGER," +
                COLUMN_LANG + " TEXT" +
                ");";


        private static final String DB_NAME = "movies_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public MoviesHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
            Log.i("DATABASE","moviesHepler");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.i("DATABASE","oncreate");
                db.execSQL(CREATE_TABLE_UPCOMMING);
                Log.i("DATABASE","table created");
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "poutses mple");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Log.i("DATABASE","onUpgrade");
                db.execSQL(" DROP TABLE " + TABLE_UP_COMMING + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException exception) {
                L.t(mContext, exception + "poutses mple 2");
            }
        }
    }


}