package com.example.ckr97.myapplication;

import android.os.AsyncTask;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mIsDestroy = true;
    }

    private class FactorialTask extends AsyncTask <Integer, Double, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar1.setMax(100);
            progressBar1.setProgress(0);

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressBar1.setProgress(100);
            mText1.setText(""+integer);

            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            int pro = (int) values[0].doubleValue();
            int res = (int) values[1].doubleValue();
            progressBar1.setProgress(pro);
            mText1.setText(""+res);

            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int iArg = integers[0];
            int iResult = 1;
            //run

            for (int i=1 ; i<= iArg ; i++) {
                iResult *= i;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i/(double)iArg*100, (double)iResult);
            }

            return iResult;
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
                FactorialTask task = new FactorialTask();
                task.execute(5);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FactorialTask task = new FactorialTask();
                task.execute(5);

                FactorialTask task2 = new FactorialTask();
                task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 10);
                //AsyncTask 병행 처리를 위해 executeOnExecutor 사용
            }
        });
    }
}

