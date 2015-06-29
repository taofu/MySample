package com.practice.justkeep.pullrefresh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.practice.justkeep.R;

/**
 * Created by taofu
 * data 2015/3/17.
 */
public class TestPullRefreshActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullrefresh);
        if (savedInstanceState == null) {

        }   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SwipeRefreshFragment fragment = new SwipeRefreshFragment();
        transaction.replace(R.id.content,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
         super.onCreateOptionsMenu(menu);
        return true;
    }
}
