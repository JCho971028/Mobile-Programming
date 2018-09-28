package com.example.ckr97.teamproject;
//한남대학교 20160745 조재은

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WhenActivity extends AppCompatActivity {
    static final String TAG = "WhenTag";
    TextView mText;
    EditText mDateEdit1, mDateEdit2, mTimeEdit1, mTimeEdit2;
    Spinner mSpinner;
    Button mFinBtn;
    SuccessHandler successHandler;
    FailHandler failHandler;
    Toolbar toolbar;

    Calendar mCalDate1 = Calendar.getInstance();
    Calendar mCalDate2 = Calendar.getInstance();
    Calendar mCalTime1 = Calendar.getInstance();
    Calendar mCalTime2 = Calendar.getInstance();
    Calendar mMinDate = Calendar.getInstance();
    Calendar mNextDate = Calendar.getInstance();
    String myFormat = "yyyy.MM.dd";
    String SendDate1, SendDate2, SendTime1, SendTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_when);

        mText = (TextView) findViewById(R.id.WhenText);
        mDateEdit1 = (EditText) findViewById(R.id.DateEdit1);
        mDateEdit2 = (EditText) findViewById(R.id.DateEdit2);
        mTimeEdit1 = (EditText) findViewById(R.id.TimeEdit1);
        mTimeEdit2 = (EditText) findViewById(R.id.TimeEdit2);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mFinBtn = (Button) findViewById(R.id.FinishBtnN);
        toolbar = (Toolbar) findViewById(R.id.toolbarWhen);

        final int MyColor = Color.parseColor("#FFCC0000");

        toolbar.setTitle("WHEN");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(MyColor);

        successHandler = new SuccessHandler();
        failHandler = new FailHandler();

        mText.setText("위치를 전송할 시간을 입력하세요.");

        SimpleDateFormat mFormatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat mFormatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat mFormatDay = new SimpleDateFormat("dd");
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        int mYear = Integer.parseInt(mFormatYear.format(mDate));
        int mMonth = Integer.parseInt(mFormatMonth.format(mDate))-1;
        int mDay = Integer.parseInt(mFormatDay.format(mDate));

        mMinDate.set(mYear, mMonth, mDay);

        //mDateEdit 달력 다이얼로그 설정
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
                mCalDate1.set(Calendar.YEAR, year);
                mCalDate1.set(Calendar.MONTH, monthOfYear);
                mCalDate1.set(Calendar.DAY_OF_MONTH, dayOfYear);
                updateLabel1();
            }
        };
        mDateEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(WhenActivity.this, R.style.DialogTheme, date1,
                        mCalDate1.get(Calendar.YEAR), mCalDate1.get(Calendar.MONTH), mCalDate1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(mMinDate.getTime().getTime());
                datePickerDialog.show();
            }
        });

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
                mCalDate2.set(Calendar.YEAR, year);
                mCalDate2.set(Calendar.MONTH, monthOfYear);
                mCalDate2.set(Calendar.DAY_OF_MONTH, dayOfYear);
                updateLabel2();
            }
        };
        mDateEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(WhenActivity.this, R.style.DialogTheme, date2,
                        mCalDate2.get(Calendar.YEAR), mCalDate2.get(Calendar.MONTH), mCalDate2.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(mMinDate.getTime().getTime());
                datePickerDialog.show();
            }
        });

        //mDateEdit 시간 다이얼로그 설정
        mTimeEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = mCalTime1.get(Calendar.HOUR_OF_DAY);
                int min = mCalTime1.get(Calendar.MINUTE);

                TimePickerDialog mTimeDialog = new TimePickerDialog(WhenActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        if (selectedHour == 0 && selectedMin == 0) {
                            mTimeEdit1.setText(selectedHour+"0 : 0"+selectedMin);
                        }
                        else if (selectedHour == 0) {
                            mTimeEdit1.setText(selectedHour+"0 : "+selectedMin);
                        }
                        else if (selectedMin == 0) {
                            mTimeEdit1.setText(selectedHour+" : 0"+selectedMin);
                        }
                        else {
                            mTimeEdit1.setText(selectedHour+" : "+selectedMin);
                        }

                        if ((selectedHour < 10) && (selectedMin < 10)) {
                            SendTime1 = "0"+selectedHour+"0"+selectedMin;
                        }
                        else if (selectedMin < 10) {
                            SendTime1 = selectedHour+"0"+selectedMin;
                        }
                        else if (selectedHour < 10) {
                            SendTime1 = "0"+selectedHour+selectedMin;
                        }
                        else {
                            SendTime1 = selectedHour+""+selectedMin;
                        }
                    }
                }, hour, min, true);
                mTimeDialog.show();
            }
        });
        mTimeEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = mCalTime2.get(Calendar.HOUR_OF_DAY);
                int min = mCalTime2.get(Calendar.MINUTE);

                TimePickerDialog mTimeDialog = new TimePickerDialog(WhenActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMin) {
                        if (selectedHour == 0 && selectedMin == 0) {
                            mTimeEdit2.setText(selectedHour+"0 : 0"+selectedMin);
                        }
                        else if (selectedHour == 0) {
                            mTimeEdit2.setText(selectedHour+"0 : "+selectedMin);
                        }
                        else if (selectedMin == 0) {
                            mTimeEdit2.setText(selectedHour+" : 0"+selectedMin);
                        }
                        else {
                            mTimeEdit2.setText(selectedHour+" : "+selectedMin);
                        }

                        if ((selectedHour < 10) && (selectedMin < 10)) {
                            SendTime2 = "0"+selectedHour+"0"+selectedMin;
                        }
                        else if (selectedMin < 10) {
                            SendTime2 = selectedHour+"0"+selectedMin;
                        }
                        else if (selectedHour < 10) {
                            SendTime2 = "0"+selectedHour+selectedMin;
                        }
                        else {
                            SendTime2 = selectedHour+""+selectedMin;
                        }
                    }
                }, hour, min, true);
                mTimeDialog.show();
            }
        });

        //spinner 시간 간격 설정
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //finishBtn 이벤트 처리
        mFinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datebox1 = mDateEdit1.getText().toString();
                String datebox2 = mDateEdit2.getText().toString();
                String timebox1 = mTimeEdit1.getText().toString();
                String timebox2 = mTimeEdit2.getText().toString();

                if (!datebox1.equals("") && !datebox2.equals("") && !timebox1.equals("") && !timebox2.equals("")) {
                    NetworkThread thread = new NetworkThread();
                    thread.start();
                }
                else {
                    Toast.makeText(WhenActivity.this, "날짜 및 시간을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateLabel1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.KOREA);
        mDateEdit1.setText(simpleDateFormat.format(mCalDate1.getTime()));
    }
    private void updateLabel2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.KOREA);
        mDateEdit2.setText(simpleDateFormat.format(mCalDate2.getTime()));
    }

    class NetworkThread extends Thread {
        @Override
        public void run() {
            super.run();
            String IntervalText = mSpinner.getSelectedItem().toString();

            try {
                Socket socket = new Socket("192.168.0.9", 9999);

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String mDate1 = mDateEdit1.getText().toString();
                String mDate2 = mDateEdit2.getText().toString();

                String Date1_1 = mDate1.split("\\.")[0];
                String Date1_2 = mDate1.split("\\.")[1];
                String Date1_3 = mDate1.split("\\.")[2];
                String Date2_1 = mDate2.split("\\.")[0];
                String Date2_2 = mDate2.split("\\.")[1];
                String Date2_3 = mDate2.split("\\.")[2];

                SendDate1 = Date1_1+Date1_2+Date1_3;
                SendDate2 = Date2_1+Date2_2+Date2_3;

                dos.writeInt(2);
                dos.writeUTF(SendDate1);
                dos.writeUTF(SendDate2);
                dos.writeUTF(SendTime1);
                dos.writeUTF(SendTime2);
                dos.writeUTF(IntervalText);

                successHandler.sendEmptyMessage(0);
                socket.close();

                Intent intent = new Intent(WhenActivity.this, AccessActivity.class);
                startActivity(intent);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
                failHandler.sendEmptyMessage(0);
            }
        }
    }

    class SuccessHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(WhenActivity.this, "시간을 저장하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    class FailHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(WhenActivity.this, "시간 저장에 실패하였습니다.\n다시 시도해주십시오.", Toast.LENGTH_SHORT).show();
        }
    }
}