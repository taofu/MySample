package com.practice.justkeep.uncaughtexceptionehandler;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.practice.justkeep.R;

/**
 * Created by taofu on 2015/1/9.
 */
public class TestOtherActvity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_main ,null);
        setContentView(relativeLayout);

        Button button = new Button(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Exit.exit();
                //startActivity(new Intent(getApplicationContext(), TestOtherActvity.class));
            }
        });

        button.setText("start");

        relativeLayout.addView(button);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
