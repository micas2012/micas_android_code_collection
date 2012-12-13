package com.diandianwan.app.learning.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import com.diandianwan.app.R;

/**
 * Date: 12-12-11 PM8:56 By yuanyang
 */
public class RatingDemo extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        //定义星级
        ratingBar.setNumStars(5);
        //设置默认
        ratingBar.setRating(3);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                Toast.makeText(RatingDemo.this,
                        "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();

            }
        });

    }
}