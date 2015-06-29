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
public class TestOverviewScreenActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);


        Button button = new Button(this);


        button.setText("Test");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TestOverviewScreenActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                intent.putExtra("count", 3);
                startActivity(intent);


            }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(button,params);
        setContentView(relativeLayout);

    }
}
