package com.zosimadis.ilias.materialdesign.com.zosimadis.ilias.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.zosimadis.ilias.materialdesign.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragmnet extends Fragment {


    public static final String KEY_USER_LEARNED = "user_learned_drawer";
    public static final String PREF_FILE_NAME = "nav_pref";
    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private RecyclerAdapter adapter;
    private android.support.v7.widget.Toolbar mToolbar;

    public NavigationDrawerFragmnet() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(getFromPreference(getActivity(), KEY_USER_LEARNED, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer_fragmnet, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerId);
        adapter = new RecyclerAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp, R.drawable.ic_navigate_next_black_24dp};
        String[] titles = {"ilias", "jaf", "ilias", "jaf", "ilias", "jaf","ilias", "jaf"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            Information current = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;

    }

    public void setUp(int nav_fragment_id, final DrawerLayout drawerLayout, android.support.v7.widget.Toolbar toolbar) {

        mToolbar = toolbar;
        containerView = getActivity().findViewById(nav_fragment_id);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreference(getActivity(), KEY_USER_LEARNED, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.8) {
                    mToolbar.setAlpha(1 - slideOffset);
                }
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreference(Context context, String prefName, String prefValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();

    }

    public static String getFromPreference(Context context, String prefName, String defaultVaule) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, defaultVaule);

    }

}
