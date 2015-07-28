package com.zosimadis.ilias.materialdesign.com.zosimadis.ilias.materialdesign.Network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zosimadis.ilias.materialdesign.com.zosimadis.ilias.materialdesign.MyApplication;

/**
 * Created by ilias on 28/7/2015.
 */
public class VolleySingleton {

    private static VolleySingleton mInstance = null;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getApplicationContenxt());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private LruCache<String,Bitmap> cache = new LruCache<>((int) ((Runtime.getRuntime().maxMemory()/1024)/8));

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });

    }

    public static VolleySingleton getInstance() {
        if (mInstance == null) {
            synchronized (VolleySingleton.class){
                if(mInstance == null)
                    mInstance = new VolleySingleton();
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {return mImageLoader;}

}
