package com.example.ckr97.ididentifier;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView TV_Cre_ID1, TV_Cre_ID2, TV_Cre_ID3, TV_Chk_ID;
    EditText ET_Chk_1, ET_Chk_2;
    Button Btn_Create, Btn_Check;
    RadioGroup radioGroup;
    Switch Switch_ID;
    LinearLayout Layout_Cre, Layout_Chk;
    Calendar calendar = Calendar.getInstance();

    private final String TAG = "11111111111";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch_ID = (Switch)findViewById(R.id.Switch_ID);
        radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        Btn_Check = (Button)findViewById(R.id.Btn_Chk_Check);
        Btn_Create = (Button)findViewById(R.id.Btn_Cre_Create);
        ET_Chk_1 = (EditText)findViewById(R.id.eT_Chk_1);
        ET_Chk_2 = (EditText)findViewById(R.id.eT_Chk_2);
        TV_Cre_ID1 = (TextView)findViewById(R.id.tV_Cre_ID1);
        TV_Cre_ID2 = (TextView)findViewById(R.id.tV_Cre_ID2);
        TV_Cre_ID3 = (TextView)findViewById(R.id.tV_Cre_ID3);
        TV_Chk_ID = (TextView)findViewById(R.id.tV_Chk_ID);
        Layout_Cre = (LinearLayout)findViewById(R.id.Layout_Cre);
        Layout_Chk = (LinearLayout)findViewById(R.id.Layout_Chk);

        //주민번호 체크 레이아웃과 생성 레이아웃을 따로 만들어 스위치를 동작시킬때 visibility를 gone 또는 visible로 설정한다.
        Switch_ID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Layout_Cre.setVisibility(View.VISIBLE);
                    Layout_Chk.setVisibility(View.GONE);
                }
                else {
                    Layout_Cre.setVisibility(View.GONE);
                    Layout_Chk.setVisibility(View.VISIBLE);
                }
                ET_Chk_1.setText("");
                ET_Chk_2.setText("");
                TV_Chk_ID.setText("");
                TV_Cre_ID1.setText("");
                TV_Cre_ID2.setText("");
                TV_Cre_ID3.setText("");
            }
        });

        /*************** IDENTIFY LAYOUT ****************/
        //editText에 들어갈 DatePickerDialog 설정
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        ET_Chk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ET_Chk_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int delete, int newcnt) {
//                Toast.makeText(getApplicationContext(), "start = [ "+start+" ]\ndelete = [ "+delete+" ]\nnewcnt = [ "+newcnt+" ]", Toast.LENGTH_SHORT).show();
                //만약 두번째 editText에 8자리 이상으로 입력하면 토스트메세지가 뜨게 한다.
                if (start >= 7) {
                    Toast.makeText(getApplicationContext(),"7자리 이하로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*************** IDENTIFY LAYOUT ****************/
    }
    /*************** IDENTIFY LAYOUT ****************/
    //datePicker로 선택한 날짜 형식 지정
    private void updateLabel() {
        String myFormat = "yyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.KOREA);

        ET_Chk_1.setText(simpleDateFormat.format(calendar.getTime()));
    }
    //CHECK 버튼 눌렀을 시
    public void onBtnCheck(View view) {
        String EditTextForward = ET_Chk_1.getText().toString();
        String EditTextBackward = ET_Chk_2.getText().toString();
        TV_Chk_ID.setText("");
        //첫번째 editText와 두번째 editText가 빈칸인지 확인
        if (EditTextForward.equals("") || EditTextBackward.equals("")) {
            Toast.makeText(getApplicationContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }   //두번째 editText의 길이가 7을 초과한다면 다시 입력받기 위해 비운다.
        else if (EditTextBackward.length() > 7) {
            Toast.makeText(getApplicationContext(), "뒤자리는 7자리 이하로 입력하세요.", Toast.LENGTH_SHORT).show();
            StringBuilder stringBuilder = new StringBuilder(EditTextBackward);
            stringBuilder.setLength(0);
            ET_Chk_2.setText(stringBuilder.toString());
            return;
        }

        String input = EditTextForward + EditTextBackward;

        if (input.length() != 13) {
            Toast.makeText(getApplicationContext(), "자릿수(13)를 맞추어 제대로 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        int[] data = new int[input.length()];
        int IDChk = 0;
        int x = 2;
        //EditText로 입력받은 숫자를 정수형으로 변환
        for (int z=0 ; z<input.length() ; z++) {
            data[z] = input.charAt(z) - '0';
        }
        //연산과정
        for (int z=0 ; z<12 ; z++) {
            IDChk += data[z] * x;
            x++;
            if (x >= 10)
                x = 2;
        }

        IDChk %= 11;
        IDChk = 11 - IDChk;

        if (IDChk == 10)
            IDChk = 0;
        //만약 끝자리와 동일하다면 유효하고, 아니면 아니라고 setText한다.
        if (data[12] == IDChk)
            TV_Chk_ID.setText("유효한 ID입니다.");
        else
            TV_Chk_ID.setText("유효하지 않은 ID입니다.");
    }
    /*************** IDENTIFY LAYOUT ****************/

    /*************** CREATE LAYOUT ****************/
    public void onBtnCreate(View view) {
        //CREATE 버튼을 누른다면 라디오id를 받아와 남성의 주민번호인지 여성의 주민번호인지 구분
        int radioId = radioGroup.getCheckedRadioButtonId();
        int gender;

        if (radioId == R.id.RadioButton_Male) {
            gender = 1;
        }
        else if (radioId == R.id.RadioButton_Female) {
            gender = 2;
        }
        else
            return;
        //주민번호를 생성하는 CreateID 함수를 불러서 return값이 true가 될 때까지 반복한다.
        while (true){
            if (CreateID(gender)) {
                break;
            }
        }
    }

    private boolean CreateID(int gender){
        int[] data = new int[13];
        int IDChk = 0, x = 2;

        for (int z=0 ; z<13 ; z++) {
            data[z] = (int)(Math.random()*9);
            if (z == 5)
                z++;
        }
        data[6] = gender;

        for (int z=0 ; z<12 ; z++) {
            IDChk += data[z] * x;
            x++;
            if (x >= 10)
                x = 2;
        }

        IDChk %= 11;
        IDChk = 11 - IDChk;

        if (IDChk == 10)
            IDChk = 0;
        //무작위로 만들어진 숫자가 조건을 만족한다면 textView에 setText한다.
        if (data[12] == IDChk){
            String Forward = ""+data[0]+data[1]+data[2]+data[3]+data[4]+data[5];
            String Backward = ""+data[6]+data[7]+data[8]+data[9]+data[10]+data[11]+data[12];
            TV_Cre_ID1.setText(Forward);
            TV_Cre_ID2.setText("-");
            TV_Cre_ID3.setText(Backward);
        }

        return (data[12] == IDChk);
    }
    /*************** CREATE LAYOUT ****************/
}
