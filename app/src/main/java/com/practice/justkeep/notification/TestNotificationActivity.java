package com.practice.justkeep.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.practice.justkeep.R;

/**
 * Created by taofu on 2015/1/22.
 */
public class TestNotificationActivity extends FragmentActivity{

    private int i = 0;
    NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyMgr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_main ,null);
        setContentView(relativeLayout);
         mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setContentTitle("My notification")
                .setContentText("Hello World!");
        mBuilder.setProgress(100, i, false);
        mBuilder.setColor(Color.RED);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher1);
        mBuilder.setLargeIcon(bitmapDrawable.getBitmap());

        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i !=  100){
                    i = i+10;

                    mBuilder.setProgress(100, i, false);
                    Notification compat = mBuilder.build();
                    compat.flags |= NotificationCompat.FLAG_NO_CLEAR;
                    mNotifyMgr.notify(001, compat);
                }else{

                    Notification compat = mBuilder.build();
                    compat.flags &= ~NotificationCompat.FLAG_NO_CLEAR;
                    mNotifyMgr.notify(001, compat);
                }
            }
        });

        button.setText("start");

        relativeLayout.addView(button);
    }
}
