package com.practice.justkeep.uncaughtexceptionehandler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by taofu on 2015/1/12.
 */
public class TestService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // Logger.d("service onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


       /* if(startId == 10){

        }*/
    }
}
