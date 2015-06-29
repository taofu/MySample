package com.practice.justkeep.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.practice.justkeep.R;

import java.util.ArrayList;

/**
 * Created by taofu
 * data 2015/3/23.
 */
public class TestViewPagerActivity extends FragmentActivity {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private ArrayList<String> mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        mTitle = new ArrayList<>();

        for(int i = 0; i < 10 ;i++){
            mTitle.add("Title " + i);
        }
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.viewpager_slidingtablayout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);

       mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
           @Override
           public int getIndicatorColor(int position) {
               return Color.WHITE;
           }

           @Override
           public int getDividerColor(int position) {
               return Color.TRANSPARENT;
           }
       });

    }


    private FragmentStatePagerAdapter mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            Bundle bundle =new Bundle();
            bundle.putInt("index",position);
            return FragmentPage.getInstance(bundle);
        }

        @Override
        public int getCount() {
            return mTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    };
}
