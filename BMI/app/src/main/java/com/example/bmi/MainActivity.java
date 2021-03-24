package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Weight;
    private EditText Height;
    private RadioButton Man;
    private RadioButton Women;
    private Button Btn;
    private TextView Bmi;
    private TextView Zhenduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Weight =findViewById(R.id.TZ);
        Height =findViewById(R.id.SG);
        Man =findViewById(R.id.NAN);
        Women =findViewById(R.id.NV);
        Btn =findViewById(R.id.Button);
        Bmi =findViewById(R.id.result);
        Zhenduan =findViewById(R.id.ZD);



        Btn.setOnClickListener(v -> {
            final String wei=Weight.getText().toString().trim();
            final String hei=Height.getText().toString().trim();
            if (wei.equals("") ) {
                Toast.makeText(MainActivity.this, "体重不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (hei.equals("")){
                Toast.makeText(MainActivity.this, "身高不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else {
                double a=Double.parseDouble(wei);
                double b=Double.parseDouble(hei);
                double bmi=a/(b*b);
                String str =String.format("%.2f", bmi);
                Bmi.setText(str);

                if(Man.isChecked()==true){
                    if (bmi<20){
                        Zhenduan.setText("体重过轻");
                    }
                    else if (bmi > 20 && bmi < 25) {
                        Zhenduan.setText("体重正常");
                    }
                    else if (bmi > 25 && bmi < 27) {
                        Zhenduan.setText("体重超重");
                    }
                    else if (bmi > 27 && bmi < 30) {
                        Zhenduan.setText("轻度肥胖");
                    }
                    else if (bmi > 30 && bmi < 35) {
                        Zhenduan.setText("中度肥胖");
                    } else {
                        Zhenduan.setText("重度肥胖");
                    }
                }
                if(Women.isChecked()==true){

                    if (bmi < 19) {
                        Zhenduan.setText("体重过轻");
                    } else if (bmi > 19 && bmi < 24) {
                        Zhenduan.setText("体重正常");
                    }
                    else if (bmi > 24 && bmi < 26) {
                        Zhenduan.setText("体重超重");
                    }
                    else if (bmi > 26 && bmi < 29) {
                        Zhenduan.setText("轻度肥胖");
                    }
                    else if (bmi > 29 && bmi < 34) {
                        Zhenduan.setText("中度肥胖");
                    } else {
                        Zhenduan.setText("重度肥胖");
                    }
                }
            }

        });
    }
}