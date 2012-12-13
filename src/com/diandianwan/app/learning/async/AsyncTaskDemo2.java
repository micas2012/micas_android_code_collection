package com.diandianwan.app.learning.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class AsyncTaskDemo2 extends Activity {
    TextView mTextView;
    Button mButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mButton = new Button(this);
        mButton.setText("Get text from network");

        mTextView = new TextView(this);
        mTextView.setText("I am the original text");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTextAsyncTask().execute("hi from network");
            }
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(mTextView);
        linearLayout.addView(mButton);

        setContentView(linearLayout);


    }

    private class DownloadTextAsyncTask extends AsyncTask<String, Void, String> {
        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */
        protected String doInBackground(String... urls) {
            return getTextFromNetwork(urls[0]);
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(String result) {
            mTextView.setText(result);
        }
    }

    private String getTextFromNetwork(String url) {
        return url;
    }
}