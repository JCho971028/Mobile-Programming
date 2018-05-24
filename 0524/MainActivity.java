package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Networking";
    TextView mText;
    Button mBtn;

    class NetworkThread extends Thread {
        @Override
        public void run() {
            try {
                Socket socket = new Socket("203.247.38.53", 9712);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                int msg = dis.readInt();
                dos.writeInt(1997);
                Log.v(TAG, "Message said \""+msg+"\"");

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.run();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.textView);
        mBtn = findViewById(R.id.button);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkThread thread = new NetworkThread();
                thread.start();
            }
        });
    }
}
