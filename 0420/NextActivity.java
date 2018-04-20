package com.example.ckr97.exintentdatatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        mText = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();
        int data1 = intent.getIntExtra("data1", 0);
        double data2 = intent.getDoubleExtra("data2", 0);
        String data3 = intent.getStringExtra("data3");

        mText.setText("data1 : "+data1+"\n");
        mText.append("data2 : "+data2+"\n");
        mText.append("data3 : "+data3);
    }

    public void finishActivity(View view) {
        proFinish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            proFinish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.main1 :
                finishActivity(item.getActionView());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void proFinish() {
        Intent intent = new Intent();

        intent.putExtra("value1", 500);
        intent.putExtra("value2", 55.55);
        intent.putExtra("value3", "반갑습니다");

        setResult(RESULT_OK, intent);
        finish();
    }
}
