package com.example.user.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity { //동적은 extends TabActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        //정적
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("1st Spec");
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator("MAIN");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("2nd Spec");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("MAIN");
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("3rd Spec");
        tabSpec3.setContent(R.id.tab3);
        tabSpec3.setIndicator("MAIN");
        tabHost.addTab(tabSpec3);


        //동적
//        TabHost.TabSpec tab1 = tabHost.newTabSpec("First tab");
//        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second tab");
//
//        tab1.setIndicator("Function A");
//        tab1.setContent(new Intent(this, Tap1Activity.class));
//        tab2.setIndicator("Function B");
//        tab2.setContent(new Intent(this, Tap2Activity.class));
//
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);
//
//        tabHost.setCurrentTab(0);
    }
}
