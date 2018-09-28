package com.example.ckr97.teamproject;
//한남대학교 20160745 조재은

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WhoActivity extends AppCompatActivity {
    static final String TAG = "WhoTag";
    TextView mText;
    EditText mPhoneEdit;
    Button mFinBtn;
    SuccessHandler successHandler;
    FailHandler failHandler;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);

        mText = (TextView) findViewById(R.id.WhoText);
        mPhoneEdit = (EditText) findViewById(R.id.WhoEdit);
        mFinBtn = (Button) findViewById(R.id.FinishBtnO);
        toolbar = (Toolbar) findViewById(R.id.toolbarwho);

        int MyColor = Color.parseColor("#FFCC0000");

        toolbar.setTitle("WHO");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setBackgroundColor(MyColor);

        successHandler = new SuccessHandler();
        failHandler = new FailHandler();

        mText.setText("위치를 전송 할 번호를 입력하세요.");

        mFinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(mPhoneEdit.getText().toString()).equals("")) {
                    NetworkThread thread = new NetworkThread();
                    thread.start();
                }
                else {
                    Toast.makeText(WhoActivity.this, "번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class NetworkThread extends Thread{
        @Override
        public void run() {
            super.run();
            String PhoneNum = mPhoneEdit.getText().toString();

            try {
                Socket socket = new Socket("192.168.0.9", 9999);

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(1);
                dos.writeUTF(PhoneNum);

                successHandler.sendEmptyMessage(0);
                socket.close();

                Intent intent = new Intent(WhoActivity.this, AccessActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                failHandler.sendEmptyMessage(0);
            }
        }
    }

    class SuccessHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(WhoActivity.this, "번호를 저장하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    class FailHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(WhoActivity.this, "번호 저장에 실패하였습니다.\n다시 시도해주십시오.", Toast.LENGTH_SHORT).show();
        }
    }
}

//이상한 폰번호로 저장 못하게 하기