package com.practice.justkeep.uncaughtexceptionehandler;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.practice.justkeep.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by taofu on 2015/1/9.
 */
public class TextUnCaughtExceptionHandler extends FragmentActivity {

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Logger.d("TextUnCaughtExceptionHandler oncrate");

        relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_main ,null);
        setContentView(relativeLayout);

        //Logger.d("TextUnCaughtExceptionHandler getDefaultUncaughtExceptionHandler = %s",Thread.getDefaultUncaughtExceptionHandler().hashCode());
        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
          //      Logger.d("TextUnCaughtExceptionHandler exception handler .... uncaughtException");
                String error = Log.getStackTraceString(ex);
                long startTime = System.currentTimeMillis();
                String key = MD5(error);
            //    Logger.i(key + " time = %s",System.currentTimeMillis() - startTime);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(),"很抱歉程序出现异常,请等待自动重启",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

              /*  Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);*/
            }
        };



        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
      //  Logger.d("TextUnCaughtExceptionHandler set setDefaultUncaughtExceptionHandler = %s",exceptionHandler.hashCode());
        final Button button = new Button(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // System.exit(0);
                String str = null;
                str.toString();
              /*  button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Exit.exit();
                    }
                },20000);
                startActivity(new Intent(getApplicationContext(), OtherActivity.class));*/
            }
        });

        button.setText("start");

        relativeLayout.addView(button);
    }
    private static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // System.exit(0);
    }
}
