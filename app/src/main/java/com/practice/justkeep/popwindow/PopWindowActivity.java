package com.practice.justkeep.popwindow;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.practice.justkeep.R;

/**
 * Created by taofu
 * data 2015/3/10.
 */
public class PopWindowActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_window);
    }

    public void onClick(View view){
        PopView popView = new PopView(this);
        //popView.showAtLocation(view, Gravity.CENTER,0,0);
        popView.show(view);
    }
}
