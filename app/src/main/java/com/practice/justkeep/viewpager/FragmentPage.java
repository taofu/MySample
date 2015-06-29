package com.practice.justkeep.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by taofu
 * data 2015/3/23.
 */
public class FragmentPage extends Fragment {


    public static FragmentPage getInstance(Bundle bundle){
        FragmentPage page = new FragmentPage();

        if(bundle != null){
            page.setArguments(bundle);
        }

        return page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText("Page" + getArguments().getInt("index"));
        return textView;
    }
}
