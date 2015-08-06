package com.zosimadis.ilias.materialdesign.Log;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ilias on 6/8/2015.
 */
public class L {

    public static void m(String Tag,String message){
        Log.d(Tag,message);
    }

    public static void t(Context context , String message){
        Toast.makeText(context,message+" ",Toast.LENGTH_LONG).show();
    }
}
