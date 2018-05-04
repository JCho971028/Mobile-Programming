package com.example.user.myapplication;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {
    FirstFragment firstFragment;
    SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    protected void switchFragment(int id) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (id == 0){
            fragmentTransaction.replace(R.id.FrameLayout2, firstFragment);
        }
        else if (id == 1) {
            fragmentTransaction.replace(R.id.FrameLayout2, secondFragment);
        }
        fragmentTransaction.commit();
    }

    public void onFirst(View view){
        switchFragment(0);
    }

    public void onSecond(View view) {
        switchFragment(1);
    }
}
