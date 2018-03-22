package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button btn1;
    Button btn2;
    Button btn3;

    private final String TAG = "11111111111111111111";

    class FocusListener implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View view, boolean b) {
            if (view == btn1)
                Log.i(TAG, "Focus on btn1");
            else if (view == btn2)
                Log.i(TAG, "Focus on btn2");
            else if (view == text)
                Log.i(TAG,"Focus on TextView");
        }
    }

    class TouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == btn1)
                Log.i(TAG, "Touch btn1");
            else if (view == btn2)
                Log.i(TAG, "Touch btn2");
            else if (view == text)
                Log.i(TAG,"Touch TextView");
            return false;
        }
    }

    class KeyListener implements View.OnKeyListener{
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (view == btn1)
                Log.i(TAG, "Key in btn1");
            else if (view == btn2)
                Log.i(TAG, "Key in btn2");
            else if (view == text)
                Log.i(TAG,"Key in TextView");
            return false;
        }
    }

    class LongClickListener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View view) {
            if (view == btn1)
                Log.i(TAG, "Long clicked btn1");
            else if (view == btn2)
                Log.i(TAG, "Long clicked btn2");
            return false;
        }
    }

    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn1)
                Log.i(TAG, "clicked btn1");
            else if (view == btn2)
                Log.i(TAG, "clicked btn2");
        }
    }

    private void close(){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int act = event.getAction();

        if(keyCode == KeyEvent.KEYCODE_BACK)
            close();

        Toast.makeText(this, "keyed in Activity "+keyCode, Toast.LENGTH_SHORT).show();

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int act = event.getAction();

        switch (act) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,"Touched in Activity"+x+", "+y);
        }

        return super.onTouchEvent(event);
    }

    public void onBtn3(View v) {
        if (v == btn3)
            text.setText("버튼3을 눌렀습니다.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.textView1);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);

        text.setFocusable(true);

        ClickListener clickListener = new ClickListener();
        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);

        LongClickListener longClickListener = new LongClickListener();
        btn1.setOnLongClickListener(longClickListener);
        btn2.setOnLongClickListener(longClickListener);

        FocusListener focusListener = new FocusListener();
        btn1.setOnFocusChangeListener(focusListener);
        btn2.setOnFocusChangeListener(focusListener);
        text.setOnFocusChangeListener(focusListener);

        TouchListener touchListener = new TouchListener();
        btn1.setOnTouchListener(touchListener);
        btn2.setOnTouchListener(touchListener);
        text.setOnTouchListener(touchListener);

        btn1.setOnKeyListener(new KeyListener());
        btn2.setOnKeyListener(new KeyListener());   //무명클래스로 정의
    }
}
