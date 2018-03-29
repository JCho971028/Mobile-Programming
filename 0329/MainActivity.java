package com.example.user.myapplication;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button BtnGet, BtnClr;
    Switch aSwitch;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView1);
        editText = (EditText)findViewById(R.id.editText1);
        BtnGet = (Button)findViewById(R.id.button1);
        BtnClr = (Button)findViewById(R.id.button2);
        aSwitch = (Switch)findViewById(R.id.switch1);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);

        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setHint("안녕하세요!");
        editText.setHintTextColor(Color.parseColor("#ff0000"));
        editText.setBackground(null);

        BtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "겟또★", Toast.LENGTH_SHORT).show();
//                String str = editText.getText().toString();
//                textView.setText(str);

                String str = editText.getText().toString();

                if (str.equals(""))
                    return;

                int radioId = radioGroup.getCheckedRadioButtonId();

                if (radioId == R.id.radioButton1) {
                    Float c = Float.parseFloat(str);
                    Float f = 9 / (float)5 * c + 32;

                    textView.setText("" + f);
                }
                else if (radioId == R.id.radioButton2) {
                    Float f = Float.parseFloat(str);
                    Float c = (f - 32) * 5 / (float)9;

                    textView.setText("" + c);
                }
                else {
                    return;
                }

                aSwitch.setChecked(true);
            }
        });

        BtnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");

//                if (aSwitch.isChecked()) {
//                    textView.setText("On");
//                }
//                else {
//                    textView.setText("Off");
//                }

                aSwitch.setChecked(false);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int delete, int newcnt) {
//          얘만!
//                Toast.makeText(getApplicationContext(), "start = [ "+start+" ]\ndelete = [ "+delete+" ]\nnewcnt = [ "+newcnt+" ]", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    textView.setText("On");
//                }
//                else
//                    textView.setText("Off");
//            }
//        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1){

                }
                else if (i == R.id.radioButton2) {

                }
                else {

                }
            }
        });
    }
}
