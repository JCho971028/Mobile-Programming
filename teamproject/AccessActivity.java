package com.example.ckr97.teamproject;
//한남대학교 20160745 조재은

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessActivity extends AppCompatActivity {
    final String TAG = "11111111111111";
    private SharedPreferences preferences;
    Button mWhoBtn, mWhenBtn, mRunBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        mWhoBtn = (Button) findViewById(R.id.WhoBtn);
        mWhenBtn = (Button) findViewById(R.id.WhenBtn);
        mRunBtn = (Button) findViewById(R.id.StartBtn);
        toolbar = (Toolbar) findViewById(R.id.toolbarAccess);

        toolbar.setTitle("Send 위치");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(Color.parseColor("#FFCC0000"));

        preferences = getSharedPreferences("pref", MODE_PRIVATE);



        mWhoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessActivity.this, WhoActivity.class);
                startActivity(intent);
                finish();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("Who", true);
                editor.commit();
            }
        });

        mWhenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccessActivity.this, WhenActivity.class);
                startActivity(intent);
                finish();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("When", true);
                editor.commit();
            }
        });

        mRunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGetLocation()) {
                    Boolean mIsWhoDone = preferences.getBoolean("Who", false);
                    Boolean mIsWhenDone = preferences.getBoolean("When", false);

                    if (mIsWhoDone && mIsWhenDone) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("When", false);
                        editor.putBoolean("Who", false);
                        editor.commit();

                        Intent intent = new Intent(AccessActivity.this, RunningActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(AccessActivity.this, "Who와 When을 설정하고 실행해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showSettingsAlert();
                }
            }
        });

    }

    public void showSettingsAlert(){
        /**한남대학교 20160698 원준식**/

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccessActivity.this, R.style.DialogTheme);

        alertDialog.setTitle("GPS 연결이 설정되지 않음");
        alertDialog.setMessage("GPS 설정이 되지 않았습니다. \n 설정창으로 가시겠습니까?");

        // OK 를 누르게 되면 설정창으로 이동합니다.

        alertDialog.setPositiveButton("설정",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        AccessActivity.this.startActivity(intent);
                    }
                });

        // Cancle 하면 종료 합니다.

        alertDialog.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public boolean isGetLocation() {
        LocationManager manager = (LocationManager)AccessActivity.this.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


}