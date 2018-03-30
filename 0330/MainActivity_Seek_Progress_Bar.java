package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    ProgressBar progressBar;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText("on Progress Changed : "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText("on Start Tracking Touch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("on Stop Tracking Touch");
            }
        });
    }

    public void onBtnStart(View v){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onBtnStop(View v) {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
