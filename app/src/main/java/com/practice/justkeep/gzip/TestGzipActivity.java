package com.practice.justkeep.gzip;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.practice.justkeep.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by taofu
 * data 2015/2/3.
 */
public class TestGzipActivity extends FragmentActivity{

    public static File dir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            dir = getExternalCacheDir();
        }
        if(!dir.exists())
            dir.mkdirs();
        new Thread(new Runnable() {
            @Override
            public void run() {

                String content = "java java java java java java java javajava java java javajava java java javajava java java javajava java java java";

                try {
                    byte [] arr = content.getBytes("utf-8");

                    Log.i("Test","original length = " + arr.length);

                    byte compress [] = compress(arr);
                    Log.i("Test","original length = " + compress.length);

                    byte unCompress [] = unCompress(compress);

                    Log.i("Test","uncompress  = " + new String(unCompress,"utf-8"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
               /* File file = new File(dir,"test.txt");
                if(file.exists()){
                    read();
                }else{
                    write(content);

                }*/

                //read();
            }
        }).start();
    }

    private static byte [] compress(byte []  data){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream stream = new GZIPOutputStream(outputStream);

            stream.write(data);
            stream.flush();
            stream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;

    }

    private static byte [] unCompress(byte [] data){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

        try {
            GZIPInputStream stream = new GZIPInputStream(inputStream);

            int len = 0;

            byte buf [] = new byte [256];

            while ((len = stream.read(buf)) > 0){
                outputStream.write(buf,0,len);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void write(String content){
        File file = new File(dir,"test.txt");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            GZIPOutputStream stream = new GZIPOutputStream(outputStream);

            stream.write(content.getBytes("utf-8"));
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void read(){
        File file = new File(dir,"test.txt");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            GZIPInputStream stream = new GZIPInputStream(inputStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = 0;

            byte buf [] = new byte [256];

            while ((len = stream.read(buf)) > 0){
                outputStream.write(buf,0,len);
            }

            Log.i("Test",outputStream.toString());

            stream.close();

            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
