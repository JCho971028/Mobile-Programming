package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    int hour, min;
//    TimePicker timePicker;
//    Calendar cal;
//    TextView textView;

    int year, month, day;
    DatePicker datePicker;
    Calendar cal;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        timePicker = (TimePicker)findViewById(R.id.timePicker);
//        textView = (TextView)findViewById(R.id.textView);
//
//        cal = Calendar.getInstance();
//        hour = cal.get(cal.HOUR_OF_DAY);
//        min = cal.get(cal.MINUTE);
//
//        timePicker.setOnTimeChangedListener(this);

        textView = (TextView)findViewById(R.id.textView2);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(this);

        cal = Calendar.getInstance();

        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH);
        day = cal.get(cal.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View view) {
        if(view == button) {
            int intMonth = datePicker.getMonth() + 1;
            textView.setText("선택한 날짜 [ "+ datePicker.getYear()+"/"+intMonth+"/"+datePicker.getDayOfMonth()+" ]");
        }
    }

//    @Override
//    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
//        textView.setText("현재 설정 시간 [ "+hour+":"+min+" ]");
//    }
}
