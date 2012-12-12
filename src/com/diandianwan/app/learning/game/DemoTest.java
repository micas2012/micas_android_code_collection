package com.diandianwan.app.learning.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class DemoTest extends Activity {
    private DemoView view;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new DemoView(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
    }
}