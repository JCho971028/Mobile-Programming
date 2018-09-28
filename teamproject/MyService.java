package com.example.ckr97.teamproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ckr97 on 2018-06-04.
 * 한남대학교 20160745 조재은
 * Message의 위치받아오는 부분은 한남대학교 20160698 원준식
 */

public class MyService extends Service{
    int mRequestCode = 9999;
    final String TAG = "11111111111111";

    private SharedPreferences preferences;
    EndHandler endHandler;
    NotificationManager manager;
    PendingIntent sender;
    private GpsInfo gps;
    boolean mRunning;

    @Override
    public void onCreate() {
        super.onCreate();
        mRunning = true;

        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        endHandler = new EndHandler();

        NetworkThread thread = new NetworkThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SystemClock.sleep(1000);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.noticon)
                .setColor(Color.parseColor("#ffcc0000"))
                .setContentTitle("Send 위치")
                .setContentText("지금 내 위치를 전송 중 입니다.")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setOngoing(true);
        manager.notify(9999, builder.build());

        CommandStart commandStart = new CommandStart();
        commandStart.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.cancel(9999);
        Log.v(TAG, "문자메세지 종료");
        SetStop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void SetSending() {
        final String PhoneNum = preferences.getString("PhoneNum","");
        final String IntervalText = preferences.getString("Interval","");
        long IntervalTime = Long.parseLong(IntervalText)*1000*60;
        long time = System.currentTimeMillis();

        try {
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            sender = PendingIntent.getBroadcast(
                    MyService.this, mRequestCode, new Intent("SMS_SENT_ACTION"), 0);
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, IntervalTime, sender);

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Toast.makeText(getApplicationContext(), "위치 정보 발신 완료", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "문자메세지 전송");

                    gps = new GpsInfo(MyService.this);

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    double altitude = gps.getAltitude();
                    String provider = gps.getProvider();
                    float accuracy = gps.getAccuracy();
                    int IntAccur = Math.round(accuracy);

                    String strLat = String.format("%.2f", latitude);
                    String strLong = String.format("%.2f", longitude);
                    String strAlti = String.format("%.1f", altitude);
                    String strAc = String.valueOf(IntAccur);
                    String strProv;

                    if (provider.equals("network")) {
                        strProv = "실내";
                    }
                    else {
                        strProv = "실외";
                    }

                    final String Message = "저는 지금 위도 "+strLat+" 경도 "+strLong+" 에 위치해있어요.\n" +
                            "정확도는 "+strAc+"%입니다. 저는 지금 "+strProv+"에요.";
                    final String URLMessage = "http://maps.google.com/?q="+latitude+","+longitude;

                    SmsManager mSmsManager = SmsManager.getDefault();
                    mSmsManager.sendTextMessage(PhoneNum, null, Message, null, null);
                    mSmsManager.sendTextMessage(PhoneNum, null, URLMessage, null, null);
                }
            }, new IntentFilter("SMS_SENT_ACTION"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SetStop() {
        mRunning = false;
        Intent intent = new Intent();
        sender = PendingIntent.getBroadcast(MyService.this, mRequestCode, intent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(sender);
        endHandler.sendEmptyMessage(0);
    }

    private String getDate() {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMdd");
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        String mNowDate = mFormat.format(mDate);

        return mNowDate;
    }

    private String getTime() {
        SimpleDateFormat mFormat = new SimpleDateFormat("HHmm");
        long mNow = System.currentTimeMillis();
        Date mTime = new Date(mNow);
        String mNowTime = mFormat.format(mTime);
        return mNowTime;
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

                dos.writeInt(4);

                PhoneNum = dis.readUTF();
                DateText1 = dis.readUTF();
                DateText2 = dis.readUTF();
                TimeText1 = dis.readUTF();
                TimeText2 = dis.readUTF();
                IntervalText = dis.readUTF();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("PhoneNum", PhoneNum);
                editor.putString("Date1", DateText1);
                editor.putString("Date2", DateText2);
                editor.putString("Time1", TimeText1);
                editor.putString("Time2", TimeText2);
                editor.putString("Interval", IntervalText);
                editor.commit();

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class CommandStart extends Thread {
        @Override
        public void run() {
            super.run();

            int i = 0;
            String mDate1 = preferences.getString("Date1","");
            String mDate2 = preferences.getString("Date2","");
            String mTime1 = preferences.getString("Time1","");
            String mTime2 = preferences.getString("Time2","");

            while (mRunning) {
                String GetData = getDate();
                String GetTime = getTime();
                Log.v(TAG, "mRunning : "+mRunning);
                Log.v(TAG, mDate1+" = "+GetData +"  /  "+ mTime1+" = "+GetTime);
                Log.v(TAG, mDate2+" = "+GetData +"  /  "+ mTime2+" = "+GetTime);

                if (mDate1.equals(GetData) && mTime1.equals(GetTime)) {
                    Log.v(TAG, "문자메세지 서비스 시작");
                    SetSending();
                }
                else if (mDate2.equals(getDate()) && mTime2.equals(getTime())) {
                    Log.v(TAG, "문자메세지 서비스 종료");
                    onDestroy();
                    break;
                }

                SystemClock.sleep(60000); i++;
            }
        }
    }

    class EndHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getApplicationContext(), "위치 정보 발신 서비스를 종료합니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), AccessActivity.class);
            startActivity(intent);
        }
    }
}
