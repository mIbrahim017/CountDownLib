package com.example.mohamedibrahim.countdowntimer;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by mohamed.ibrahim on 8/1/2016.
 */

public class CountDownView {


    public static final long FIVE_MINUTES = 300;     // 5 minutes  to seonds


    //    private final long startTime = FOUR_MINUTES;
    private final long interval = 1000; // interval one second
    private long elapsedTime;


    private TextView mTextView;
    private CountDownListener mListener;


    public void setRemainingTime(long seconds) {
        // convert seconds to miliseconds
        elapsedTime = TimeUnit.SECONDS.toMillis(seconds);
    }

    private Handler mHandler = new Handler();


    private Runnable mCountDown = new Runnable() {
        public void run() {
            if (elapsedTime > 0) {
                elapsedTime -= interval;
                setTextViewVal(getTimeFormat());
                postHandlerDelay();
            } else {
//                cancel();
                clean();
                if (mListener != null)
                    mListener.onCountDownEnd();
            }
        }
    };

    private String getTimeFormat() {

        long secs = elapsedTime / 1000;
        long mins = (elapsedTime / 1000) / 60;

        secs = secs % 60;
        String seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        }

        String minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        }


        return minutes + " : " + seconds;
    }

    public CountDownView(TextView textView) {
        this.mTextView = textView;

    }


    public void start() {
        removeHandlerCallback();
        setTextViewVal(String.valueOf("00:00"));
        setTextViewVisibility(true);
        postHandler();

        if (mListener != null) mListener.onCountStart();

    }


    public void clean() {
        mHandler = null;
        mCountDown = null;
        mListener = null;
        mTextView = null;

    }


    public void cancel() {
        removeHandlerCallback();
        setTextViewVal("00:00");
        if (mListener != null) mListener.onCountDownEnd();

    }


    /**
     * Helper method
     */


    private void postHandlerDelay() {
        if (isValid()) mHandler.postDelayed(mCountDown, interval);
    }

    private void postHandler() {
        if (isValid()) mHandler.post(mCountDown);
    }


    private void removeHandlerCallback() {
        if (isValid()) mHandler.removeCallbacks(mCountDown);
    }


    private void setTextViewVisibility(boolean isVisible) {
        if (isValidView()) mTextView.setVisibility(isVisible ? View.VISIBLE : View.GONE);

    }

    private void setTextViewVal(String val) {
        if (isValidView()) mTextView.setText(val);
    }

    private boolean isValid() {
        return mHandler != null && mCountDown != null;
    }

    private boolean isValidView() {
        return mTextView != null;
    }

    public void setCountDownListener(CountDownListener mListener) {
        this.mListener = mListener;
    }

    public long getRemainingTime() {
        return elapsedTime;
    }


}
