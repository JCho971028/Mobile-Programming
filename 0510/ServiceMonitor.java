package com.example.user.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by User on 2018-05-10.
 */

public class ServiceMonitor extends Service {
    final String TAG = "ServiceMonitor";
    MediaPlayer mediaPlayer;

    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        ServiceMonitor getService() {
            return ServiceMonitor.this;
        }
    }

    public ServiceMonitor() {
        super();
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "OnCreate");

        mediaPlayer = MediaPlayer.create(this, R.raw.test);

        super.onCreate();
    }

    //startService
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "OnStartCommand");

        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "OnDestroy");

        mediaPlayer.stop();

        super.onDestroy();
    }

    //bindService
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");

        mediaPlayer.start();

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    void VolumeUp() {
        mediaPlayer.setVolume(1.0f, 1.0f);
    }

    void VolumeDown() {
        mediaPlayer.setVolume(0.0f,0.0f);
    }
}
