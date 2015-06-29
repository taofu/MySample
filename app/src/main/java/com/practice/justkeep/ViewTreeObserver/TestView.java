package com.practice.justkeep.ViewTreeObserver;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

/**
 * Created by taofu
 * data 2015/4/16.
 */
public class TestView extends RelativeLayout {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("Test","dispatchTouchEvent" +  onFilterTouchEventForSecurity(ev));
        testsss();
        return super.dispatchTouchEvent(ev);
    }

    private void testsss(){
        try {
            Field field =  View.class.getDeclaredField("mViewFlags");
            if(field != null){
                field.setAccessible(true);

                int value = field.getInt(this);

                boolean tag = (( value & 0x00000020 ) == 0x00000000);
                Log.i("Test"," tag = " + tag );
            }
            Field field1 = View.class.getDeclaredField("mListenerInfo");

            if(field1 != null){
                field1.setAccessible(true);
                Object o = field1.get(this);

                Field [] fields =  o.getClass().getDeclaredFields();

                if(fields != null){
                    for(Field f : fields){
                        f.setAccessible(true);
                        Log.i("Test","mListener " + f.getName() + " value is  " +( f.get(o) == null ? " null " : " not null"));
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
