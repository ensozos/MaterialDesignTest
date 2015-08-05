package com.zosimadis.ilias.materialdesign.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zosimadis.ilias.materialdesign.Fragments.FragmentCollections;
import com.zosimadis.ilias.materialdesign.Fragments.FragmentSearch;
import com.zosimadis.ilias.materialdesign.Fragments.FragmentUpcoming;
import com.zosimadis.ilias.materialdesign.Fragments.Myfragment;
import com.zosimadis.ilias.materialdesign.R;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private android.support.v7.widget.Toolbar toolbar;

    public static final int MOVIE_SEARCH_RESULT = 1;
    public static final int MOVIE_UPCOMING_RESULT = 2;
    public static final int NOT_SURE_YET = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.id_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragmnet drawerFragmnet = (NavigationDrawerFragmnet)
                getFragmentManager().findFragmentById(R.id.nav_fragment_id);

        drawerFragmnet.setUp(R.id.nav_fragment_id, (DrawerLayout) findViewById(R.id.drawer_layout_id), toolbar);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        //when the page changes in the ViewPager, update the Tabs accordingly
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }
        });
        //Add all the Tabs to the TabHost
        for (int i = 0; i < mAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            .setTabListener(this));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.navigate) {
            startActivity(new Intent(this, NextActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int num) {
//
//            Fragment fragment = null;
//            switch (num) {
//                case MOVIE_SEARCH_RESULT:
//                    fragment = FragmentSearch.newInstance("","");
//                    break;
//                case MOVIE_UPCOMING_RESULT:
//                    fragment = FragmentUpcoming.newInstance("","");
//                    break;
//                case NOT_SURE_YET:
//                    fragment = FragmentCollections.newInstance("","");
//                    break;
//            }
            return Myfragment.getInstance(num);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }


    }

}
