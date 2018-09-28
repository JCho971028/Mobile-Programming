package com.example.ckr97.teamproject;
//한남대학교 20160745 조재은

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RunningActivity extends AppCompatActivity {
    final String TAG = "11111111111111";
    Button mStopBtn;
    TextView mWhoTextView, mWhenTextView;
    SuccessHandler successHandler;
    FailHandler failHandler;
    Toolbar toolbar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        mWhoTextView = (TextView) findViewById(R.id.WhoTextView);
        mWhenTextView = (TextView) findViewById(R.id.WhenTextView);
        mStopBtn = (Button) findViewById(R.id.StopBtn);
        toolbar = (Toolbar) findViewById(R.id.toolbarRun);

        final int MyColor = Color.parseColor("#FFCC0000");

        toolbar.setTitle("Send 위치");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(MyColor);

        successHandler = new SuccessHandler();
        failHandler = new FailHandler();
        preferences = getSharedPreferences("pref", MODE_PRIVATE);

        NetworkThread thread = new NetworkThread();
        thread.start();

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RunningActivity.this, MyService.class);
                stopService(intent);

                Intent GoIntent = new Intent(RunningActivity.this, AccessActivity.class);
                startActivity(GoIntent);
            }
        });
    }

    class NetworkThread extends Thread {
        @Override
        public void run() {
            super.run();

            try {
                String PhoneNum, DateText1, DateText2, TimeText1, TimeText2, IntervalText;

                Socket socket = new Socket("192.168.0.9", 9999);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeInt(3);

                PhoneNum = dis.readUTF();
                DateText1 = dis.readUTF();
                DateText2 = dis.readUTF();
                TimeText1 = dis.readUTF();
                TimeText2 = dis.readUTF();
                IntervalText = dis.readUTF();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("PhoneNum", PhoneNum);
                editor.putString("DateText1", DateText1);
                editor.putString("DateText2", DateText2);
                editor.putString("TimeText1", TimeText1);
                editor.putString("TimeText2", TimeText2);
                editor.putString("IntervalText", IntervalText);
                editor.commit();

                Log.v(TAG, PhoneNum + " / " + DateText1 + "~" + DateText2 + " / " + TimeText1 + "~" + TimeText2 + " / " + IntervalText);

                successHandler.sendEmptyMessage(0);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                failHandler.sendEmptyMessage(0);
            }
        }
    }

    class SuccessHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(RunningActivity.this, "서비스를 실행합니다.", Toast.LENGTH_SHORT).show();

            String PhoneNum = preferences.getString("PhoneNum","");
            String DateText1 = preferences.getString("DateText1","");
            String DateText2 = preferences.getString("DateText2","");
            String TimeText1 = preferences.getString("TimeText1","");
            String TimeText2 = preferences.getString("TimeText2","");
            String IntervalText = preferences.getString("IntervalText","");

            char[] ADate1 = DateText1.toCharArray();
            char[] ADate2 = DateText2.toCharArray();
            char[] ATime1 = TimeText1.toCharArray();
            char[] ATime2 = TimeText2.toCharArray();

            String Date1YYYY = ADate1[0]+""+ADate1[1]+""+ADate1[2]+""+ADate1[3]+"";
            String Date1MM = ADate1[4]+""+ADate1[5]+"";
            String Date1DD = ADate1[6]+""+ADate1[7]+"";
            String Time1HH = ATime1[0]+""+ATime1[1]+"";
            String Time1MM = ATime1[2]+""+ATime1[3]+"";
            String Date2YYYY = ADate2[0]+""+ADate2[1]+""+ADate2[2]+""+ADate2[3]+"";
            String Date2MM = ADate2[4]+""+ADate2[5]+"";
            String Date2DD = ADate2[6]+""+ADate2[7]+"";
            String Time2HH = ATime2[0]+""+ATime2[1]+"";
            String Time2MM = ATime2[2]+""+ATime2[3]+"";

            mWhoTextView.setText(PhoneNum);
            mWhenTextView.setText(Date1YYYY+"년 "+Date1MM+"월 "+Date1DD+"일 "+Time1HH+"시 "+Time1MM+"분 부터\n");
            mWhenTextView.append(Date2YYYY+"년 "+Date2MM+"월 "+Date2DD+"일 "+Time2HH+"시 "+Time2MM+"분 까지\n");
            mWhenTextView.append(IntervalText+ "분 간격으로");

            Intent intent = new Intent(RunningActivity.this, MyService.class);
            startService(intent);
        }
    }

    class FailHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(RunningActivity.this, "오류 발생\n다시 시도하시길 바랍니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
