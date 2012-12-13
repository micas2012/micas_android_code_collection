package com.diandianwan.app.learning.async;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Date: 12-12-8 AM10:22 By yuanyang
 */
public class HandlerDemo extends Activity {
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            System.out.println(msg);
            switch (msg.what) {
                case 0:
                    Toast t1 = Toast.makeText(HandlerDemo.this, "ss", Toast.LENGTH_SHORT);
                    t1.show();
                case 1:
                    Toast t2 = Toast.makeText(HandlerDemo.this, "t2", Toast.LENGTH_SHORT);
                    t2.show();
            }
            super.handleMessage(msg);
        }
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button b1 = new Button(this);
        b1.setText("按钮1");
        Button b2 = new Button(this);
        b2.setText("按钮2");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = new Message();
                msg.what = 1;
                mHandler.sendMessage(msg);
            }
        });
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.addView(b1);
        linearLayout.addView(b2);
        setContentView(linearLayout);


    }
}