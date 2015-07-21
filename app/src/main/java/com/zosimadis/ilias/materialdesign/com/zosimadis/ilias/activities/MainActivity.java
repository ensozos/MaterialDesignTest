package com.zosimadis.ilias.materialdesign.com.zosimadis.ilias.activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zosimadis.ilias.materialdesign.R;


public class MainActivity extends ActionBarActivity {

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.id_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragmnet drawerFragmnet = (NavigationDrawerFragmnet)
                getFragmentManager().findFragmentById(R.id.nav_fragment_id);

        drawerFragmnet.setUp(R.id.nav_fragment_id ,(DrawerLayout)  findViewById(R.id.drawer_layout_id),toolbar);
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
}
