package com.zosimadis.ilias.materialdesign.Activities;

import android.app.Application;
import android.content.Context;

/**
 * Created by ilias on 28/7/2015.
 * This class is important to configure libraries
 * also to pass the application context to volley singleton
 *
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public MyApplication getInstance(){
        return mInstance;
    }

    public static Context getApplicationContenxt(){
        return mInstance.getApplicationContext();
    }



}
