package com.example.ckr97.myapplication;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String TAG = "1111111111111111111";

    TextView mText1, mText2;
    Button mBtn1, mBtn2;
    ProgressBar progressBar1, progressBar2;
    boolean mIsDestroy = false;
    int mCnt = 0;
    MyHandler myHandler = new MyHandler();

    Handler handler = new Handler();

//    class MyThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//
//            while(!mIsDestroy) {
//                long t = System.currentTimeMillis();
////                mText1.setText(""+t);
//                SystemClock.sleep(1000);
//                Log.v(TAG, ""+t);
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mIsDestroy = true;
    }

//    class AddThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//
//            while (!mIsDestroy) {
//                SystemClock.sleep(1000);
//                Log.v(TAG , "AddThread = "+mCnt++);
//            }
//        }
//    }
//
//    class SubThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//
//            while (!mIsDestroy) {
//                SystemClock.sleep(1000);
//                Log.v(TAG , "SubThread = "+mCnt--);
//            }
//        }
//    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1234) {
                mText1.setText(""+msg.obj);
            }
        }
    }

    class MyHandThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!mIsDestroy){
                SystemClock.sleep(1000);
                Message msg = new Message();
                msg.what = 1234;
                msg.obj = System.currentTimeMillis();

                myHandler.sendMessage(msg);
            }
        }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            long t = System.currentTimeMillis();
            mText2.setText(""+t);
            handler.post(this);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText1 = (TextView)findViewById(R.id.textView1);
        mText2 = (TextView)findViewById(R.id.textView2);
        mBtn1 = (Button) findViewById(R.id.button1);
        mBtn2 = (Button) findViewById(R.id.button2);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyThread myThread = new MyThread();
//                myThread.start();

//                AddThread addThread = new AddThread();
//                addThread.start();
//                SubThread subThread = new SubThread();
//                subThread.start();

                MyHandThread myHandThread = new MyHandThread();
                myHandThread.start();
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRunnable myRunnable = new MyRunnable();
                handler.post(myRunnable);
            }
        });
    }
}

