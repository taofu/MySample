package com.practice.justkeep.uncaughtexceptionehandler;

import android.os.*;

/**
 * Created by taofu on 2015/1/12.
 */
public class Exit {

    public static void  exit(){

        //System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.runFinalization();
    }
}
