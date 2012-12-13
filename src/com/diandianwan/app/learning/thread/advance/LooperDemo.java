package com.diandianwan.app.learning.thread.advance;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * @deprecated
 */
public class LooperDemo extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *
         *  Returns the application's main looper,
         *  which lives in the main thread of the application.
         *
         */
        Looper looper = Looper.getMainLooper();
        Handler handler = new Handler();
        handler.removeMessages(0);
    }

    class LooperThread extends Thread {
        public Handler handler;

        public void run() {
            Looper.prepare();
            //此时，是绑定在当前线程的。
            handler = new Handler() {
                public void handleMessage(Message message) {


                }
            };
            Looper.loop();

        }

    }
}