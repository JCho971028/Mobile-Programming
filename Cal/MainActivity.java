package com.example.ckr97.intentcal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    final String TAG = "11111111111111";

    EditText mEditNum1, mEditNum2;
    RadioGroup radioGroup;
    Button mBtnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditNum1 = (EditText)findViewById(R.id.editText);
        mEditNum2 = (EditText)findViewById(R.id.editText2);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        mBtnCal = (Button)findViewById(R.id.buttonCal);

        mBtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
                String Num1 = mEditNum1.getText().toString();
                String Num2 = mEditNum2.getText().toString();
                double result;

                int id = radioGroup.getCheckedRadioButtonId();

                switch (id) {
                    case R.id.radioButtonPlus :
                        result = Double.parseDouble(Num1)+Double.parseDouble(Num2);
                        intent.putExtra("result", result);
                        break;

                    case R.id.radioButtonMinus :
                        result = Double.parseDouble(Num1)-Double.parseDouble(Num2);
                        intent.putExtra("result", result);
                        break;

                    case R.id.radioButtonMul :
                        result = Double.parseDouble(Num1)*Double.parseDouble(Num2);
                        intent.putExtra("result", result);
                        break;

                    case R.id.radioButtonDiv :
                        result = Double.parseDouble(Num1)/Double.parseDouble(Num2);
                        intent.putExtra("result", result);
                        break;
                }

                startActivity(intent);
            }
        });
    }
}
