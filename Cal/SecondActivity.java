package com.example.ckr97.intentcal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerActivity extends AppCompatActivity {
    TextView textView;
    double id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        textView = (TextView)findViewById(R.id.textViewAnswer);

        Intent intent = getIntent();
        id = intent.getDoubleExtra("result", 0.0);
        textView.setText(""+id);
    }

    @Override
    public void finishActivity(int requestCode) {
        ProFinish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ProFinish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ProFinish() {
        Toast.makeText(getApplicationContext(),"결과값은 "+id+"입니다", Toast.LENGTH_LONG).show();
        finish();
    }
}
