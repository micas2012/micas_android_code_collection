package com.diandianwan.app.learning.game;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class ResultActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog(0);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(R.drawable.sym_def_app_icon).setTitle("test ")
                    .setMessage("这是一个测试")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent();
                            intent.setClass(ResultActivity.this, DemoTest.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", "yy");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
            return alert.create();
        }
        return super.onCreateDialog(id);

    }
}