package com.example.user.myapplication;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mText;
    ListView mList;
    SensorManager mSensorManager;
    List<Sensor> mSensorList;

    String [] names = {"정보통신트랙", "컴퓨터트랙", "보안트랙", "무인트랙"};

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
//            return names.length;
            return mSensorList.size();
        }

        @Override
        public Object getItem(int i) {
//            return names[i];
            return mSensorList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Sensor s = mSensorList.get(i);

            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView ItemText = new TextView(getApplicationContext());
//            ItemText.setText(names[i]);
            ItemText.setText(s.getName());
            ItemText.setTextColor(Color.argb(255, 0,255,0));
            ItemText.setTextSize(20);
            linearLayout.addView(ItemText);

            TextView ItemText2 = new TextView(getApplicationContext());
            ItemText2.setText(s.getVendor());
            ItemText2.setTextColor(Color.argb(255, 0,0,255));
            ItemText2.setTextSize(20);
            linearLayout.addView(ItemText2);

            return linearLayout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        mText = findViewById(R.id.textView);
        mList = findViewById(R.id.listVIew);

        mList.setAdapter(new MyAdapter());
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sensor s = mSensorList.get(i);
                mText.setText(s.getName());
                mText.setTextSize(20);
                mText.setTextColor(Color.argb(255, 0, 0, 0));
            }
        });
    }
}
