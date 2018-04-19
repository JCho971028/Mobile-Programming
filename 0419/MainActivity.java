package com.example.ckr97.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn1 = (Button)findViewById(R.id.button1);
        mBtn2 = (Button)findViewById(R.id.button2);
        mBtn3 = (Button)findViewById(R.id.button3);
        mBtn4 = (Button)findViewById(R.id.button4);
        mBtn5 = (Button)findViewById(R.id.button5);
        mBtn6 = (Button)findViewById(R.id.button6);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);  //명시적 인텐트
                startActivity(intent);
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.354784, 127.421133"));
                startActivity(intent);
            }
        });

        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                startActivity(intent);
            }
        });

        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intent);
            }
        });

        mBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010********"));
                startActivity(intent);
            }
        });

        mBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("010-2855-3639"));
//                startActivity(intent);   //Permission 필요
                CallPhone();
            }
        });
    }

    void CallPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-0000-0000"));
            startActivity(intent);
        }
        else {
            final String[] PERMISSION_STORAGE = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CallPhone();
            }
            else {
                Toast.makeText(getApplicationContext(), "권한을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
