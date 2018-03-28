/*
Made by ) JCho971028@gmail.com
*/

package com.example.ckr97.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_process, tv_result;
    Button btn_ac, btn_c, btn_sin, btn_cos, btn_tan, btn_sqrt, btn_7, btn_8, btn_9, btn_div,
            btn_4, btn_5, btn_6, btn_multi, btn_1, btn_2, btn_3, btn_minus, btn_dot, btn_0, btn_plus, btn_equal;

    private final String TAG = "111111111111111111";
    String result = "0", process = "0", viewing = "", input = "";
    boolean sign = true;
    double arg = 0;
    char BtnPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_process = (TextView)findViewById(R.id.tV_Process);
        tv_result = (TextView)findViewById(R.id.tV_Result);
        btn_ac = (Button)findViewById(R.id.Btn_AC);
        btn_c = (Button)findViewById(R.id.Btn_C);
        btn_sin = (Button)findViewById(R.id.Btn_Sin);
        btn_cos = (Button)findViewById(R.id.Btn_Cos);
        btn_tan = (Button)findViewById(R.id.Btn_Tan);
        btn_sqrt = (Button)findViewById(R.id.Btn_Sqrt);
        btn_7 = (Button)findViewById(R.id.Btn_7);
        btn_8 = (Button)findViewById(R.id.Btn_8);
        btn_9 = (Button)findViewById(R.id.Btn_9);
        btn_div = (Button)findViewById(R.id.Btn_Division);
        btn_4 = (Button)findViewById(R.id.Btn_4);
        btn_5 = (Button)findViewById(R.id.Btn_5);
        btn_6 = (Button)findViewById(R.id.Btn_6);
        btn_multi = (Button)findViewById(R.id.Btn_Multiplication);
        btn_1 = (Button)findViewById(R.id.Btn_1);
        btn_2 = (Button)findViewById(R.id.Btn_2);
        btn_3 = (Button)findViewById(R.id.Btn_3);
        btn_minus = (Button)findViewById(R.id.Btn_Minus);
        btn_dot = (Button)findViewById(R.id.Btn_Dot);
        btn_0 = (Button)findViewById(R.id.Btn_0);
        btn_plus = (Button)findViewById(R.id.Btn_Plus);
        btn_equal = (Button)findViewById(R.id.Btn_Equal);
    }

    public void onBtnAC(View view) {
        arg = 0;
        result = "0";
        BtnPress = '0';
        viewing = "";
        input = "";
        sign = true;

        tv_process.setText(" ");
        tv_result.setText(result);
    }

    public void onBtnC(View view) {
        result = tv_result.getText().toString();

        if (result.equals("0"))
            return;
        else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(result);
            result = stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();

            tv_result.setText(result);
        }
    }

    public void onBtnSin(View view) {
        double D_result, Radian_result, Sin_result;

        result = tv_result.getText().toString();
        viewing = result;

        D_result = Double.parseDouble(result);
        Radian_result = Math.toRadians(D_result);
        Sin_result = Math.sin(Radian_result);

        double dot = Sin_result - (int)Sin_result;

        if (dot == 0.0){
            result = String.valueOf((int)Sin_result);
        }
        else {
            result = String.valueOf(Sin_result);
        }

        tv_result.setText(result);
        tv_process.setText("sin("+viewing+")");

        BtnPress = 's';
    }

    public void onBtnCos(View view) {
        double D_result, Radian_result, Cos_result;

        result = tv_result.getText().toString();
        viewing = result;

        D_result = Double.parseDouble(result);
        Radian_result = Math.toRadians(D_result);
        Cos_result = Math.cos(Radian_result);

        double dot = Cos_result - (int)Cos_result;

        if (dot == 0.0){
            result = String.valueOf((int)Cos_result);
        }
        else {
            result = String.valueOf(Cos_result);
        }

        tv_result.setText(result);
        tv_process.setText("cos("+viewing+")");

        BtnPress = 'c';
    }

    public void onBtnTan(View view) {
        double D_result, Radian_result, Tan_result;

        result = tv_result.getText().toString();
        viewing = result;

        D_result = Double.parseDouble(result);
        Radian_result = Math.toRadians(D_result);
        Tan_result = Math.tan(Radian_result);

        double dot = Tan_result - (int)Tan_result;

        if (dot == 0.0){
            result = String.valueOf((int)Tan_result);
        }
        else {
            result = String.valueOf(Tan_result);
        }

        tv_result.setText(result);
        tv_process.setText("tan("+viewing+")");

        BtnPress = 't';
    }

    public void onBtnSqrt(View view) {
        double D_result, Root_result;

        result = tv_result.getText().toString();
        viewing = result;

        D_result = Double.parseDouble(result);
        Root_result = Math.sqrt(D_result);

        double dot = Root_result - (int)Root_result;

        if (dot == 0.0){
            result = String.valueOf((int)Root_result);
        }
        else {
            result = String.valueOf(Root_result);
        }

        tv_result.setText(result);
        tv_process.setText("√("+viewing+")");

        BtnPress = 'r';
    }

    public void onBtnDiv(View view) {
        viewing = tv_result.getText().toString();
        process = tv_process.getText().toString();

        if (sign)
            input = " ÷ ";

        if (BtnPress == '+'){
            sign = false;
            onBtnPlus(view);
            sign = true;
        }
        else if (BtnPress == '-'){
            sign = false;
            onBtnMinus(view);
            sign = true;
        }
        else if (BtnPress == '*'){
            sign = false;
            onBtnMulti(view);
            sign = true;
        }

        BtnPress = '/';

        if (!result.equals("0")){
            process = process + viewing + input;
            tv_process.setText(process);
            double cal = Double.parseDouble(viewing);

            if (arg != 0) {
                arg /= cal;

                double dot = arg - (int)arg;

                if (dot == 0.0){
                    tv_result.setText(""+(int)arg);
                }
                else {
                    tv_result.setText(""+arg);
                }
            }
            else {
                arg = cal;
            }
        }
        result = "0";
    }

    public void onBtnMulti(View view) {
        viewing = tv_result.getText().toString();
        process = tv_process.getText().toString();

        if (sign)
            input = " × ";

        if (BtnPress == '+'){
            sign = false;
            onBtnPlus(view);
            sign = true;
        }
        else if (BtnPress == '/'){
            sign = false;
            onBtnDiv(view);
            sign = true;
        }
        else if (BtnPress == '-'){
            sign = false;
            onBtnMinus(view);
            sign = true;
        }

        BtnPress = '*';

        if (!result.equals("0")){
            process = process + viewing + input;
            tv_process.setText(process);
            double cal = Double.parseDouble(viewing);

            if (arg != 0) {
                arg *= cal;

                double dot = arg - (int)arg;

                if (dot == 0.0){
                    tv_result.setText(""+(int)arg);
                }
                else {
                    tv_result.setText(""+arg);
                }
            }
            else {
                arg = cal;
            }
        }
        result = "0";
    }

    public void onBtnMinus(View view) {
        viewing = tv_result.getText().toString();
        process = tv_process.getText().toString();

        if (sign)
            input = " - ";

        if (BtnPress == '+'){
            sign = false;
            onBtnPlus(view);
            sign = true;
        }
        else if (BtnPress == '/'){
            sign = false;
            onBtnDiv(view);
            sign = true;
        }
        else if (BtnPress == '*'){
            sign = false;
            onBtnMulti(view);
            sign = true;
        }

        BtnPress = '-';

        if (!result.equals("0")){
            process = process + viewing + input;
            tv_process.setText(process);
            double cal = Double.parseDouble(viewing);

            if (arg != 0){
                arg -= cal;

                double dot = arg - (int)arg;

                if (dot == 0.0){
                    tv_result.setText(""+(int)arg);
                }
                else {
                   tv_result.setText(""+arg);
                }
            }
            else {
                arg = cal;
            }
        }
        result = "0";
    }

    public void onBtnPlus(View view) {
        viewing = tv_result.getText().toString();
        process = tv_process.getText().toString();

        if (sign)
            input = " + ";

        if (BtnPress == '-'){
            sign = false;
            onBtnMinus(view);
            sign = true;
        }
        else if (BtnPress == '/'){
            sign = false;
            onBtnDiv(view);
            sign = true;
        }
        else if (BtnPress == '*'){
            sign = false;
            onBtnMulti(view);
            sign = true;
        }

        BtnPress = '+';

        double cal = Double.parseDouble(viewing);

        if (!result.equals("0")){
                process = process + viewing + input;
                tv_process.setText(process);

                arg += cal;

                double dot = arg - (int)arg;

                if (dot == 0.0){
                    tv_result.setText(""+(int)arg);
                }
                else {
                    tv_result.setText(""+arg);
                }
        }
        result = "0";
    }

    public void onBtnEqual(View view) {
        viewing = tv_result.getText().toString();
        double sum = 0.0;
        double current = Double.parseDouble(viewing);

        if (BtnPress == '+')
            sum = arg + current;
        else if (BtnPress == '-')
            sum = arg - current;
        else if (BtnPress == '*')
            sum = arg * current;
        else if (BtnPress == '/')
            sum = arg / current;

        double dot = sum - (int)sum;

        if (dot == 0.0)
            tv_result.setText(""+(int)sum);
        else
            tv_result.setText(""+sum);

        tv_process.setText(" ");

        BtnPress = '=';
    }

    public void onBtn1(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "1";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "1";
            tv_result.setText(viewing);
        }
    }

    public void onBtn2(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "2";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "2";
            tv_result.setText(viewing);
        }
    }

    public void onBtn3(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "3";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "3";
            tv_result.setText(viewing);
        }
    }

    public void onBtn4(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "4";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "4";
            tv_result.setText(viewing);
        }
    }

    public void onBtn5(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "5";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "5";
            tv_result.setText(viewing);
        }
    }

    public void onBtn6(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "6";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "6";
            tv_result.setText(viewing);
        }
    }

    public void onBtn7(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "7";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "7";
            tv_result.setText(viewing);
        }
    }

    public void onBtn8(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "8";
            tv_result.setText(result);
        }
        else
        {
            viewing = viewing + "8";
            tv_result.setText(viewing);
        }
    }

    public void onBtn9(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);

        if (result.equals("0")){
            result = "9";
            tv_result.setText(result);
        }
        else {
            viewing = viewing + "9";
            tv_result.setText(viewing);
        }

    }

    public void onBtn0(View view) {
        viewing = tv_result.getText().toString();

        if (BtnPress == '=' || BtnPress == 's' || BtnPress == 'c' || BtnPress == 't' || BtnPress == 'r')
            onBtnAC(view);
        else if ((BtnPress == '+' || BtnPress == '-' || BtnPress == '*' || BtnPress == '/') && (result.equals("0"))) {
            tv_result.setText(result);
            BtnPress = '0';
        }

        if (result.equals("0"))
            return;

        viewing = viewing + "0";

        tv_result.setText(viewing);
    }
}