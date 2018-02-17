package com.example.goro.wheelrotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private ImageView wheel;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    private TextView degree;
    private String mmCurrAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheel = findViewById(R.id.imageView1);
        degree = findViewById(R.id.tv_degree);

        mDetector = new GestureDetector(this, new MyGestureListener());
        wheel.setOnTouchListener(touchListener);
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mDetector.onTouchEvent(event);

        }
    };

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        wheel.startAnimation(rotate);
        mmCurrAngle = String.format("%.2f", mCurrAngle);
        degree.setText("The rotation degree is " + mmCurrAngle);

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            final float xc = wheel.getWidth() / 2;
            final float yc = wheel.getHeight() / 2;

            final float x = event.getX();
            final float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    wheel.clearAnimation();
                    mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mPrevAngle = mCurrAngle;
                    mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                    animate(mPrevAngle, mCurrAngle, 0);
                    break;

                }
                case MotionEvent.ACTION_UP: {
                    mPrevAngle = mCurrAngle = 0;
                    break;
                }
            }
            return true;
        }
        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG", "onDown: ");

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            final float xc = wheel.getWidth() / 2;
            final float yc = wheel.getHeight() / 2;

            final float x = e1.getX();
            final float y = e1.getY();

            Log.d("TAG", "onFling: ");
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                return false; // Right to left
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                return false; // Left to right
            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Top to bottom
            }
            return false;
        }

    }
}
