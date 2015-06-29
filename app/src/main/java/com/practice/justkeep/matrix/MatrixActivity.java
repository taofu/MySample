package com.practice.justkeep.matrix;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.practice.justkeep.R;

/**
 * Created by taofu on 15-6-5.
 */
public class MatrixActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = 144;
        params.topMargin = 144;

        relativeLayout.addView(new MatrixView(this),params);
        setContentView(relativeLayout);
    }
}
