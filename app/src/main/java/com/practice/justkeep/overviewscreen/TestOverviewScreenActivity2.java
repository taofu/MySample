package com.practice.justkeep.overviewscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by taofu on 15-6-17.
 */
public class TestOverviewScreenActivity2 extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);


        Button button = new Button(this);


        button.setText("Test2");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(button,params);
        setContentView(relativeLayout);

    }
}
