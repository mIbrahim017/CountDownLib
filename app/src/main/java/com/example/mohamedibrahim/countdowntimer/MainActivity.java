package com.example.mohamedibrahim.countdowntimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CountDownListener {

    private CountDownView countDownView;
    private TextView timer;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView) findViewById(R.id.textViewCountDown);
        status = (TextView) findViewById(R.id.textViewStatus);

        status.setText("not work");
    }

    public void startTimer(View view) {
        if (countDownView == null) countDownView = new CountDownView(timer);
        countDownView.setCountDownListener(this);
        countDownView.setRemainingTime(60);
        countDownView.start();
    }

    public void endTimer(View view) {
        if (countDownView != null) countDownView.cancel();
    }

    @Override
    public void onCountDownEnd() {
        status.setText("Count Down End");
    }

    @Override
    public void onCountStart() {
        status.setText("Count Down Start");
    }
}
