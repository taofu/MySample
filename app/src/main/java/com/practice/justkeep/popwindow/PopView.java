package com.practice.justkeep.popwindow;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.sql.BatchUpdateException;

/**
 * Created by taofu
 * data 2015/3/10.
 */
public class PopView {

    private Context mContext;
    private final PopupWindow popupWindow;

    public PopView(Context context){
       this.mContext = context;
       popupWindow = new PopupWindow(mContext);
       popupWindow.setWidth(720);
       popupWindow.setHeight(1000);
       popupWindow.setFocusable(false);
      /* popupWindow.setTouchable(true);
       popupWindow.setOutsideTouchable(true);*/
       popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
       buildView();

    }

    public void buildView(){
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(400,400));

        relativeLayout.setBackgroundColor(Color.BLACK);
        relativeLayout.setAlpha(0.6f);
        Button button = new Button(mContext);
        button.setText("close");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        relativeLayout.addView(button);
        popupWindow.setContentView(relativeLayout);
    }

    public void show(View view) {
           popupWindow.showAsDropDown(view);
    }
}
