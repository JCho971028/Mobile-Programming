package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    ListView listView1;
    String data[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTheme(android.R.style.Theme_DeviceDefault_Light);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);

        textView1.setText(getResources().getString(R.string.str2));
        textView1.setTextSize(getResources().getDimension(R.dimen.mainTextSize));
        textView1.setBackgroundColor(getResources().getColor(R.color.myTextColor));


        listView1 = (ListView)findViewById(R.id.listView1);
        data = getResources().getStringArray(R.array.str3);

        listView1.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Object getItem(int i) {
                return data[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                //중요! textView를 코드로 만드는 방법
                TextView text = new TextView(getApplicationContext());
                text.setText(data[i]);
                return text;
            }
        });
    }
}
