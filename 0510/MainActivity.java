package com.example.user.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mStartBtn, mStopBtn, mTurnOnBtn, mTurnOffBtn;
    //bindService
    ServiceMonitor serviceMonitor = null;
    boolean mIsBind = false;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServiceMonitor.MyBinder myBinder = (ServiceMonitor.MyBinder)iBinder;
            serviceMonitor = myBinder.getService();
            mIsBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartBtn = findViewById(R.id.StartBtn);
        mStopBtn = findViewById(R.id.StopBtn);
        mTurnOnBtn = findViewById(R.id.OnBtn);
        mTurnOffBtn = findViewById(R.id.OffBtn);


        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceMonitor.class);
//                startService
//                startService(intent);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceMonitor.class);
//                startService
//                stopService(intent);
                if (mIsBind) {
                    unbindService(serviceConnection);
                    mIsBind = false;
                    serviceMonitor = null;
                }
            }
        });

        //bindService
        mTurnOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (serviceMonitor != null)
                    serviceMonitor.VolumeUp();
            }
        });

        mTurnOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (serviceMonitor != null)
                    serviceMonitor.VolumeDown();
            }
        });
    }
}
