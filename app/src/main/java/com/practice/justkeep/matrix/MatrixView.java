package com.practice.justkeep.matrix;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.practice.justkeep.R;

/**
 * Created by taofu on 15-6-5.
 */
public class MatrixView extends View {

    private Paint mPaint;


    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mPaint == null) {
            mPaint = new Paint();
        }

        canvas.drawColor(Color.WHITE);



        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inDensity = DisplayMetrics.DENSITY_XHIGH;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a31, options);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int d = bitmap.getDensity();

        Log.i("Test", "w = " + w + " h = " + h + " d = " + d);


        canvas.drawBitmap(bitmap, 0, 0, mPaint);

        Log.i("Test", canvas.getMatrix().toString());

        Matrix matrix = new Matrix();

      //  matrix.setValues(new float[]{0,-0.5f,72,0.5f,0,72,0,0,1});

        //matrix.postScale(0.5f, 0.5f);
        // Log.i("Test", matrix.toString());

       // matrix.postTranslate(144, 144);
      /*  matrix.postScale(0.5f, 0.5f);

        matrix.postRotate(90,144,144);*/
        matrix.postTranslate(72, 72);
        matrix.postRotate(30);
       // matrix.postTranslate(288,0);
        Log.i("Test", matrix.toString());
        canvas.drawBitmap(bitmap, matrix, mPaint);
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(0, i * 72, 720, i * 72, mPaint);
            canvas.drawLine(i * 72, 0, i * 72, 720, mPaint);
        }

    }
}
