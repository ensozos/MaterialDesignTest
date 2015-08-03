package com.zosimadis.ilias.materialdesign.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.zosimadis.ilias.materialdesign.Network.VolleySingleton;
import com.zosimadis.ilias.materialdesign.R;

/**
 * Created by Windows on 23-01-2015.
 */
public class Myfragment extends Fragment {
    private TextView textView;

    public static Myfragment getInstance(int position) {
        Myfragment fragmentDummy = new Myfragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragmentDummy.setArguments(args);
        return fragmentDummy;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_my, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("The Page Selected Is " + bundle.getInt("position"));
        }

        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        String url = "http://developer.android.com/intl/zh-cn/training/volley/simple.html";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "response " + response, Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error " + error, Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
        return layout;

    }


}