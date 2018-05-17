package com.example.user.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.SealedObject;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final float SHAKE_SHRESHOLD = 10.0f;
    private long lastTime;

    TextView mText1, mText2, mText3, mText4;
    Button mBtn1, mBtn2;
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mText1 = findViewById(R.id.textView1);
        mText2 = findViewById(R.id.textView2);
        mText3 = findViewById(R.id.textView3);
        mText4 = findViewById(R.id.textView4);
        mBtn1 = findViewById(R.id.button1);
        mBtn2 = findViewById(R.id.button2);
    }

    public void onBtnStart(View view) {
        Sensor s = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
    }

    public void onBtnStop(View view) {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currnetTime = System.currentTimeMillis();
            long diff = currnetTime - lastTime;

            if (diff > 1000) {
                double x = sensorEvent.values[0];
                double y = sensorEvent.values[1];
                double z = sensorEvent.values[2];

                mText1.setText("x = " + x + "\n");
                mText1.append("y = " + y + "\n");
                mText1.append("z = " + z);

                double abs = Math.sqrt(x*x + y*y + z*z);

                if (abs > SHAKE_SHRESHOLD) {
                    lastTime = currnetTime;
                    Toast.makeText(this, "낙상!!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
