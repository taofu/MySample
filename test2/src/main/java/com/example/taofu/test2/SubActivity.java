package com.example.taofu.test2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by taofu on 15-5-15.
 */
public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TextView mTextView = new TextView(this);

        mTextView.setText("test sub");

        setContentView(mTextView);
    }
}
