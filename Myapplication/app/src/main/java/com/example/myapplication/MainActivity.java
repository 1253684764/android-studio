package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import java.lang.String;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {
    private EditText etname;
    private EditText etphone;
    private RadioButton man;
    private RadioButton woman;
    private CheckBox cbjava;
    private CheckBox cbandroid;
    private CheckBox cbenglish;
    private CheckBox cbmath;
    private Button btnsure;
    private String selected="";
    private RelativeLayout mainLayout;
    private RadioGroup rgbtn;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.et_name);
        etphone=findViewById(R.id.et_phone);
        man=findViewById(R.id.man);
        woman=findViewById(R.id.woman);
        cbjava=findViewById(R.id.cb_java);
        cbandroid=findViewById(R.id.cb_android);
        cbenglish=findViewById(R.id.cb_english);
        cbmath=findViewById(R.id.cb_math);
        btnsure=findViewById(R.id.btn_sure);
        mainLayout=findViewById(R.id.ll_main);
        rgbtn=findViewById(R.id.rg_btn);


        //checkbox监听事件
        cbjava.setOnCheckedChangeListener(this);
        cbandroid.setOnCheckedChangeListener(this);
        cbenglish.setOnCheckedChangeListener(this);
        cbmath.setOnCheckedChangeListener(this);
        etphone.setOnFocusChangeListener(this);

        btnsure.setOnClickListener(v -> {
            final String name=etname.getText().toString().trim();
            final String phone=etphone.getText().toString().trim();
            String sex="男";
            int id=rgbtn.getCheckedRadioButtonId();
            if (name.equals("")){
                Toast.makeText(MainActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                return;
            }else if (phone.equals("")){
                Toast.makeText(MainActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return;
            }else if(phone.matches("^1[3-9]\\d{9}$")){
                if (id==R.id.woman){
                    sex="女";
                }
                String info="用户名:"+name+" "+"手机号："+phone+" "+"性别："+sex+"\n喜欢的课程："+selected;
                Snackbar.make(mainLayout,info,Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"信息已确认",Toast.LENGTH_LONG);
                            }
                        }).show();
            }else{
                Toast.makeText(MainActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkBox=(CheckBox) buttonView;
        if (isChecked){
            selected+=checkBox.getText().toString()+",";
        }else {
            selected=selected.replace(checkBox.getText().toString()+",","");
        }
        Snackbar.make(mainLayout,selected,Snackbar.LENGTH_LONG).show();
    }

    public void hideKeyboard(){
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()){
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            hideKeyboard();
        }
    }
}