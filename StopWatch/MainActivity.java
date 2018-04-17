package com.example.ckr97.stopwatch;

import android.media.SoundPool;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.media.AudioManager.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity {
    final String TAG = "111111111111111";

    TextView mTextHour, mTextMin, mTextSec, mTextMSec,
            mTextChk1, mTextChk2, mTextChk3, mTextChk4, mTextChk5, mTextChk6, mTextChk7, mTextChk8,
            mTextNum1, mTextNum2, mTextNum3, mTextNum4, mTextNum5, mTextNum6, mTextNum7, mTextNum8;
    Button mBtnStart, mBtnLap, mBtnReset;
    SoundPool soundPool;

    int mCnt = 0;
    long mCurTime = 0;
    int mMiliSec, mSec, mMin, mHour;
    int soundId;

    Handler handler = new Handler();

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            long time = System.currentTimeMillis() - mCurTime;
//            Log.v(TAG, ""+time);

            mMiliSec = (int)time%1000;
            mSec = (int)time/1000;
            mMin = (int)time/60000;
            mHour = (int)time/3600000;

            mTextMSec.setText(""+mMiliSec);
            mTextSec.setText(""+mSec);
            mTextMin.setText(""+mMin);
            mTextHour.setText(""+mHour);

            SystemClock.sleep(1);

            handler.post(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextHour = (TextView)findViewById(R.id.textViewHour);
        mTextMin = (TextView)findViewById(R.id.textViewMin);
        mTextSec = (TextView)findViewById(R.id.textViewSec);
        mTextMSec = (TextView)findViewById(R.id.textViewMSec);
        mTextChk1 = (TextView)findViewById(R.id.textViewChk1);
        mTextChk2 = (TextView)findViewById(R.id.textViewChk2);
        mTextChk3 = (TextView)findViewById(R.id.textViewChk3);
        mTextChk4 = (TextView)findViewById(R.id.textViewChk4);
        mTextChk5 = (TextView)findViewById(R.id.textViewChk5);
        mTextChk6 = (TextView)findViewById(R.id.textViewChk6);
        mTextChk7 = (TextView)findViewById(R.id.textViewChk7);
        mTextChk8 = (TextView)findViewById(R.id.textViewChk8);
        mTextNum1 = (TextView)findViewById(R.id.textViewNum1);
        mTextNum2 = (TextView)findViewById(R.id.textViewNum2);
        mTextNum3 = (TextView)findViewById(R.id.textViewNum3);
        mTextNum4 = (TextView)findViewById(R.id.textViewNum4);
        mTextNum5 = (TextView)findViewById(R.id.textViewNum5);
        mTextNum6 = (TextView)findViewById(R.id.textViewNum6);
        mTextNum7 = (TextView)findViewById(R.id.textViewNum7);
        mTextNum8 = (TextView)findViewById(R.id.textViewNum8);

        mBtnStart = (Button)findViewById(R.id.buttonStart);
        mBtnLap = (Button)findViewById(R.id.buttonLap);
        mBtnReset = (Button)findViewById(R.id.buttonReset);

        soundPool = new SoundPool(1, STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.ddang, 1);

        final MyRunnable myRunnable = new MyRunnable();

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurTime = System.currentTimeMillis();
                mTextHour.setText("0");
                mTextMin.setText("0");
                mTextSec.setText("0");
                mTextMSec.setText("0");
                mTextChk1.setText(""); mTextNum1.setText("");
                mTextChk2.setText(""); mTextNum2.setText("");
                mTextChk3.setText(""); mTextNum3.setText("");
                mTextChk4.setText(""); mTextNum4.setText("");
                mTextChk5.setText(""); mTextNum5.setText("");
                mTextChk6.setText(""); mTextNum6.setText("");
                mTextChk7.setText(""); mTextNum7.setText("");
                mTextChk8.setText(""); mTextNum8.setText("");
                mCnt = 0;
                handler.post(myRunnable);
            }
        });

        mBtnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);

                mCnt++;
                int count = mCnt%8;
                int Hour = Integer.parseInt(mTextHour.getText().toString());
                int Min = Integer.parseInt(mTextMin.getText().toString());
                int Sec = Integer.parseInt(mTextSec.getText().toString());
                int MSec = Integer.parseInt(mTextMSec.getText().toString());

                if (count == 1) {
                    mTextNum1.setText(""+mCnt);
                    mTextChk1.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 2) {
                    mTextNum2.setText(""+mCnt);
                    mTextChk2.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 3) {
                    mTextNum3.setText(""+mCnt);
                    mTextChk3.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 4) {
                    mTextNum4.setText(""+mCnt);
                    mTextChk4.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 5) {
                    mTextNum5.setText(""+mCnt);
                    mTextChk5.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 6) {
                    mTextNum6.setText(""+mCnt);
                    mTextChk6.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 7) {
                    mTextNum7.setText(""+mCnt);
                    mTextChk7.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }
                else if (count == 0) {
                    mTextNum8.setText(""+mCnt);
                    mTextChk8.setText(Hour+" : "+Min+" : "+Sec+" : "+MSec);
                }

            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(myRunnable);

                mTextHour.setText("0");
                mTextMin.setText("0");
                mTextSec.setText("0");
                mTextMSec.setText("0");
                mTextChk1.setText(""); mTextNum1.setText("");
                mTextChk2.setText(""); mTextNum2.setText("");
                mTextChk3.setText(""); mTextNum3.setText("");
                mTextChk4.setText(""); mTextNum4.setText("");
                mTextChk5.setText(""); mTextNum5.setText("");
                mTextChk6.setText(""); mTextNum6.setText("");
                mTextChk7.setText(""); mTextNum7.setText("");
                mTextChk8.setText(""); mTextNum8.setText("");
                mCnt = 0;
            }
        });
    }
}
