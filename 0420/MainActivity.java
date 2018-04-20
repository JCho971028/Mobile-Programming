package com.example.ckr97.exintentdatatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView)findViewById(R.id.textView1);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, NextActivity.class);

        intent.putExtra("data1", 100);
        intent.putExtra("data2", 12.34);
        intent.putExtra("data3", "안녕하세요");

//        startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            int data1 = data.getIntExtra("value1", 0);
            double data2 = data.getDoubleExtra("value2", 0.0);
            String data3 = data.getStringExtra("value3");

            mText.setText("value1 : "+data1+"\n");
            mText.append("value2 : "+data2+"\n");
            mText.append("value3 : "+data3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.next1 :
                nextActivity(item.getActionView());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
