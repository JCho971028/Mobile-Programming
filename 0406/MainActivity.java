package com.example.ckr97.myapplication;

import android.media.SoundPool;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.media.AudioManager.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    TextView textView1, textView2;
    ImageView image1;
    SoundPool soundPool1;

    int gen_Id, typing_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        image1 = (ImageView) findViewById(R.id.imageView);

        image1.setImageResource(R.drawable.hnu285_76);
        image1.setScaleType(ImageView.ScaleType.CENTER);

        soundPool1 = new SoundPool(1, STREAM_MUSIC, 0);
        gen_Id = soundPool1.load(this, R.raw.gen, 1);
        typing_Id = soundPool1.load(this, R.raw.typing, 1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool1.play(gen_Id, 1, 1, 0, 1, 1);
                SystemClock.sleep(500);
                soundPool1.play(typing_Id, 1, 1, 0, 0, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool1.play(typing_Id, 1, 1, 0, 0, 1);
            }
        });
    }
}