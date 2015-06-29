package com.practice.justkeep.uncaughtexceptionehandler;

import android.content.Intent;
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
public class OtherActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Logger.d("OtherActivity oncrate " + hashCode());


        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_main ,null);
        setContentView(relativeLayout);

        Button button = new Button(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TestOtherActvity.class));
            }
        });

        button.setText("start");

        relativeLayout.addView(button);
       /*final Thread.UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Logger.d("OtherActivity getDefaultUncaughtExceptionHandler = %s",exceptionHandler.hashCode());
       Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Logger.d("OtherActivity  exception handler .... uncaughtException thread id = %s" ,thread.getId());
                //exceptionHandler.uncaughtException(thread,ex);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    //TODO
                   // exceptionHandler.uncaughtException(thread,ex);
                }else{
                    if(exceptionHandler != null){
                        exceptionHandler.uncaughtException(thread,ex);
                    }
                }


            }
        });*/

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
