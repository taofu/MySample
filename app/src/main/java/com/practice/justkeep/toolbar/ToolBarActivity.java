package com.practice.justkeep.toolbar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.practice.justkeep.R;
import com.practice.justkeep.SystemFacade;

/**
 * Created by taofu
 * data 2015/3/19.
 */
public class ToolBarActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.demo_toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drwaer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initDrawerLayout();

    }

    private void  initDrawerLayout(){
        int screenWidth = SystemFacade.getScreenWidth(this);
        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        int tooBarHeight = getResources().getDimensionPixelSize(tv.resourceId);

      //  mDrawer.setLayoutParams(new LinearLayout.LayoutParams(screenWidth - tooBarHeight, LinearLayout.LayoutParams.MATCH_PARENT));

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,R.string.openDrawerContentDes,R.string.closeDrawerContentDes) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };

        mDrawer.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(mDrawerToggle != null){
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(mDrawerToggle != null){
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_swipe_refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh){
            Toast.makeText(this,"refresh",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
