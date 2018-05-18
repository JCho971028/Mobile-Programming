package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    TextView mText;
//    GridView mGrid;
    String [] data = {
            "gird1", "grid2", "grid3", "grid4", "grid5", "grid6", "grid7", "grid8", "grid9", "grid10", "grid11", "grid12"
    };

    Spinner mSpin;
    TextView mText1, mText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText1 = findViewById(R.id.textView);
        mText2 = findViewById(R.id.textView2);
        mSpin = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, data
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpin.setAdapter(adapter);

        SpinnerListener listener = new SpinnerListener();
        mSpin.setOnItemSelectedListener(listener);

//        mText = findViewById(R.id.textView);
//        mGrid = findViewById(R.id.gridView);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, data
//        );
//
//        mGrid.setAdapter(adapter);
//
//        GridListener listener = new GridListener();
//        mGrid.setOnItemClickListener(listener);
    }

    class SpinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String str = data[i];
            mText1.setText(str);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void getSpinnerData(View view) {
        int position = mSpin.getSelectedItemPosition();
        String str = data[position];
        mText2.setText(str);
    }

//    class GridListener implements AdapterView.OnItemClickListener{
//
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            String str = data[i];
//            mText.setText(str);
//        }
//    }
}
