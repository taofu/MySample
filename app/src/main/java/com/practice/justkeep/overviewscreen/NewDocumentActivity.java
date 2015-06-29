package com.practice.justkeep.overviewscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by taofu on 15-6-17.
 */
public class NewDocumentActivity extends FragmentActivity{


    private TextView textView;
    private Button button;



    @Override
    public void onCreate(Bundle savedInstanceState/*, PersistableBundle persistentState*/) {
        super.onCreate(savedInstanceState/*,persistentState*/);


        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);



        textView = new TextView(this);

        int counter = getIntent().getIntExtra("count",-1);
       /* if(persistentState != null){
            textView.setText("this is : " + persistentState.getInt("counter") + " code " + hashCode());
        }else{*/

            textView.setText("this is : " + counter + " code " + hashCode());
       // }

        linearLayout.addView(textView);


        button = new Button(this);

        button.setText("Start");

        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),NewDocumentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
                intent.putExtra("count",DocumentCentricActivity.getCounter());

                startActivity(intent);
            }
        });
        setContentView(linearLayout);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Toast.makeText(this,"new intent",1).show();
        int counter = intent.getIntExtra("count",-2);
        textView.setText("this is onNewIntent: ");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //outState.putInt("counter", getIntent().getIntExtra("count", -5));
        super.onSaveInstanceState(outState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
