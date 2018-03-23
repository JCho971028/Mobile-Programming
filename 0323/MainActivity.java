package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    TextView textView2;
    TextView textView3;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    private final String TAG = "1111111111111111111";
    String input = "0";
    int arg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
    }

    public void onBtn0(View v) {
        Log.v(TAG, "Clicked Button1");

        input = textView2.getText().toString();

        if (input.equals("0"))
            return;

        input = input + "0";
        textView2.setText(input);
    }

    public void onBtn1(View v){
        Log.v(TAG, "Clicked Button2");

        input = textView2.getText().toString();

        if (input.equals("0"))
            input = "1";
        else
            input = input + "1";

        textView2.setText(input);
    }

    public void onBtnPlus(View v){
        Log.v(TAG, "Clicked Button3");

        int arg2 = Integer.parseInt(textView2.getText().toString());
        arg += arg2;

        input = "0";
        textView2.setText(input);
        textView3.setText(""+arg);
    }

    public void onBtnEq(View v){
        Log.v(TAG, "Clicked Button4");

        int result;
        int current = Integer.parseInt(textView2.getText().toString());

        result = current + arg;

        textView2.setText(""+result);
        textView3.setText(" ");

        input = textView2.getText().toString();
    }

    public void onBtnReset(View v){
        arg = 0;
        input = "0";

        textView2.setText(input);
        textView3.setText(" ");
    }
}
