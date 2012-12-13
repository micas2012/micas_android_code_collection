package com.diandianwan.app.learning.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.diandianwan.app.R;

/**
 * Date: 12-12-12 AM11:10 By yuanyang
 */
public class SPTest extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_core);
        TextView tv_highScore = (TextView) findViewById(R.id.high_score);
        Button btn_getHighScore = (Button) findViewById(R.id.btn_hs_get);
        Button btn_setHighScore = (Button) findViewById(R.id.btn_hs_set);
        EditText et_scoreInput = (EditText) findViewById(R.id.et_hs_input);


    }

    private void setHighScore(int newHighScore) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), newHighScore);
        editor.commit();

    }

    private long readHighScore(String key) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        long savedHS = getResources().getInteger(R.string.saved_high_score);
        long highScore = sharedPref.getInt(getString(R.string.saved_high_score), 100);
        return highScore;

    }
}