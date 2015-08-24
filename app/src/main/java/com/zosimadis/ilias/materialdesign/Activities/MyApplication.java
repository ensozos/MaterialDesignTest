package com.zosimadis.ilias.materialdesign.Activities;

import android.app.Application;
import android.content.Context;

import com.zosimadis.ilias.materialdesign.Database.DBMovies;

/**
 * Created by ilias on 28/7/2015.
 * This class is important to configure libraries
 * also to pass the application context to volley singleton
 *
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    private static DBMovies dbMovies;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public synchronized static  DBMovies getWritableDatabase(){
        if(dbMovies == null){
            dbMovies = new DBMovies(getApplicationContenxt());
        }
        return dbMovies;
    }

    public MyApplication getInstance(){
        return mInstance;
    }

    public static Context getApplicationContenxt(){
        return mInstance.getApplicationContext();
    }



}
