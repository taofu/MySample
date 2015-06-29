package com.practice.justkeep.ViewTreeObserver;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.practice.justkeep.R;

/**
 * Created by taofu
 * data 2015/4/16.
 */
public class TestViewTreeObserverActivtiy extends FragmentActivity {

    private Button visibleSelf;
    private Button visbleParent;
    private Button deleteParent;
    private Button deleteSelf;
    private TestView mParent;
    private RelativeLayout mOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activtiy_viewtreeobserver);


        mParent = (TestView) findViewById(R.id.parent);
        mOther = (RelativeLayout) findViewById(R.id.other);

        visbleParent = (Button) findViewById(R.id.visible_parent);
        visibleSelf = (Button) findViewById(R.id.visible_self);
        deleteSelf = (Button) findViewById(R.id.delete);
        deleteParent = (Button) findViewById(R.id.delete_parent);

       mParent.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               Log.i("Test"," onTouch1 " +event.getX() + "/ " + event.getY()   );
               return false;
           }
       });

       mParent.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               Log.i("Test"," onTouch2 " +event.getX() + "/ " + event.getY()   );
               return false;
           }
       });
        final View view = findViewById(R.id.group);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test"," view.getViewTreeObserver() " +  view.getViewTreeObserver().hashCode());
            }
        });

        mParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test"," mParent.getViewTreeObserver() " +  mParent.getViewTreeObserver().hashCode());
            }
        });

        mOther.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test","  mOther.getViewTreeObserver()" +  mOther.getViewTreeObserver().hashCode());
            }
        });

        visibleSelf.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test"," visibleSelf.getViewTreeObserver() " +  visibleSelf.getViewTreeObserver().hashCode());
            }
        });
        visbleParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test"," visbleParent.getViewTreeObserver() " +  visbleParent.getViewTreeObserver().hashCode());
            }
        });
        deleteSelf.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect rect1 = new Rect();
                deleteSelf.getGlobalVisibleRect(rect1);
                Log.i("Test"," deleteSelf.getViewTreeObserver() " +  deleteSelf.getViewTreeObserver().hashCode() + " parent = " + (deleteSelf.getParent() == null ? " null " : " not null") + " visible = " + deleteSelf.isShown() + " rect = " + rect1.toString());
            }
        });
        deleteParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Test"," deleteParent.getViewTreeObserver() " +  deleteParent.getViewTreeObserver().hashCode());
            }
        });





    }


    public void onClick(View view){

        int id = view.getId();

        switch (id){

            case R.id.delete:{
               // mParent.removeView(deleteSelf);
                deleteSelf.layout(10,10,deleteSelf.getWidth(),deleteSelf.getHeight());
                break;
            }

            case R.id.delete_parent:{
                ((ViewGroup)mParent.getParent()).removeView(mParent);
                break;
            }

            case R.id.visible_parent:{
                   mParent.setVisibility(View.GONE);
                break;
            }

            case R.id.visible_self:{
                visibleSelf.setVisibility(View.GONE);
                break;
            }
        }
    }
}
