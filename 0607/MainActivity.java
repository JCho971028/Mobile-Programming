package com.example.user.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText mEdit;
    TextView mText;
    Button mPrefRead, mPrefWrite, mInRead, mInWrite, mExRead, mExWrite;
    private SharedPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.EditText);
        mText = findViewById(R.id.TextView);
        mPrefRead = findViewById(R.id.BtnPrefRead);
        mPrefWrite = findViewById(R.id.BtnPrefWrite);
        mInRead = findViewById(R.id.BtnInRead);
        mInWrite = findViewById(R.id.BtnInWrite);
        mExRead = findViewById(R.id.BtnExRead);
        mExWrite = findViewById(R.id.BtnExWrite);

        mPreference = getSharedPreferences("setting", 0);

        RequestPermission();

        mPrefRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPreference.contains("key1")) {
                    String data = mPreference.getString("key1", "");

                    mText.setText(data);
                }
            }
        });

        mPrefWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = mEdit.getText().toString();

                SharedPreferences.Editor editor = mPreference.edit();
                editor.putString("key1",data);
                editor.commit();
            }
        });

        mInWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = mEdit.getText().toString();

                try {
                    FileOutputStream fos = openFileOutput("myfile.txt", Context.MODE_APPEND);
                    try {
                        fos.write(data.getBytes());

                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        mInRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput("myfile.txt");
                    try {
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);

                        mText.setText(new String(buffer));
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        mExWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = mEdit.getText().toString();
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File f = new File(path, "external.txt");

                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    try {
                        fos.write(data.getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        mExRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File f = new File(path, "external.txt");
                try {
                    FileInputStream fis = new FileInputStream(f);
                    try {
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        fis.close();
                        mText.setText(new String(buffer));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void RequestPermission() {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, 1);
        }
    }
}
