package com.diandianwan.app.learning.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Yang
 */
public class DemoView extends View implements View.OnTouchListener {

    private int sx, sy;
    private boolean moving;
    private int ex, ey;
    private Handler handler;
    private int mScreenWidth, mScreenHeight;
    private Runnable moveThread = new Runnable() {
        @Override
        public void run() {
            invalidate();
            handler.postDelayed(moveThread, 10);
        }
    };

    public DemoView(Context context) {
        super(context);
        sx = sy = 50;
        moving = false;
        handler = new Handler();
        setOnTouchListener(this);
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = windowManager.getDefaultDisplay().getWidth();
        mScreenHeight = windowManager.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        logic();
        Paint paint = new Paint();

        paint.setColor(Color.GREEN);
        Rect screen = new Rect(0, 0, mScreenWidth, mScreenHeight);
        canvas.drawRect(screen, paint);

        paint.setColor(Color.BLUE);
        Rect sq = new Rect(sx, sy, sx + 20, sy + 20);

        canvas.drawRect(sq, paint);

    }

    private void logic() {
        if (sx < ex) {
            sx++;

        }
        if (sx > ex) {
            sx--;
        }
        if (sy < ey) {
            sy++;
        }
        if (sy > ey) {
            sy--;
        }
        if (sx == ex && sy == ey) {
            handler.removeCallbacks(moveThread);
            moving = false;
            Intent intent = new Intent();
            intent.setClass(getContext(), ResultActivity.class);
            getContext().startActivity(intent);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!moving) {
            ex = (int) motionEvent.getX();
            ey = (int) motionEvent.getY();
            moving = !moving;

            handler.postAtTime(moveThread, 1);
            System.out.println("post: " + ex + "," + ey + "," + sx + "," + sy);
        } else {
            ex = (int) motionEvent.getX();
            ey = (int) motionEvent.getY();
        }
        return true;
    }


}
