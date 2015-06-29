package com.practice.justkeep.pullrefresh;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.practice.justkeep.R;

import java.util.List;

/**
 * Created by taofu
 * data 2015/3/17.
 */
public class SwipeRefreshFragment extends Fragment {

    private static final int LIST_ITEM_COUNT = 20;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_swipe_refresh_content,container,false);
// Retrieve the SwipeRefreshLayout and ListView instances
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        // BEGIN_INCLUDE (change_colors)
        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        // END_INCLUDE (change_colors)

        // Retrieve the ListView
        mListView = (ListView) view.findViewById(R.id.contentlist);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mListAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Cheeses.randomList(LIST_ITEM_COUNT));

        // Set the adapter between the ListView and its backing data.
        mListView.setAdapter(mListAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                initiateRefresh();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_swipe_refresh, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:

                // We make sure that the SwipeRefreshLayout is displaying it's refreshing indicator
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }

                // Start our refresh background task
                initiateRefresh();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initiateRefresh() {

        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
    }

    private void onRefreshComplete(List<String> result) {

        // Remove all items from the ListAdapter, and then replace them with the new items
        mListAdapter.clear();
        for (String cheese : result) {
            mListAdapter.add(cheese);
        }

        // Stop the refreshing indicator
        mSwipeRefreshLayout.setRefreshing(false);
    }
    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected List<String> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Cheeses.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }
}
