package com.diandianwan.app.learning.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.diandianwan.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class LearningLayoutInflator extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showCustomDialog();


    }

    public void showCustomDialog() {
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        Context mContext = LearningLayoutInflator.this;
        //下面俩种方法都可以
        //LayoutInflater inflater = getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.custom_dialog, null);

        TextView text = (TextView) layout.findViewById(R.id.dialog_content);
        text.setText("Hello, Welcome to Mr Wei's blog!");

        Button buttonOK = (Button) layout.findViewById(R.id.btn_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(1000);
            }
        });
        ImageView image = (ImageView) layout.findViewById(R.id.image_1);
        image.setImageResource(android.R.drawable.sym_def_app_icon);
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }

    protected void showToast(int type) {
        Toast.makeText(this, "这是一个toast测试", Toast.LENGTH_SHORT).show();

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = li.inflate(R.layout.toast, null);

        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(type);
        toast.show();
    }

    public void onClickAction(View view) {
        this.finish();
    }


}