package com.zosimadis.ilias.materialdesign.com.zosimadis.ilias.materialdesign;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        return layout;

    }


}