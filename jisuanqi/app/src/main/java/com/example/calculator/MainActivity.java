package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etresult;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btn_qk;
    private Button btn_jian;
    private Button btn_jia;
    private Button btn_cheng;
    private Button btn_chu;
    private Button btn_tui;
    private Button btn_dian;
    private Button btn_deng;
    private Button btn_baifenhao;
    boolean clear_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jisuanqi);

        //找到相应的id对应的东西
        etresult=findViewById(R.id.tv_result);
        btn_qk=findViewById(R.id.btn_c);
        btn_chu=findViewById(R.id.btn_divide);
        btn_jian=findViewById(R.id.btn_min);
        btn_jia=findViewById(R.id.btn_pass);
        btn_cheng=findViewById(R.id.btn_multiply);
        btn_tui=findViewById(R.id.btn_backspace);
        btn_dian=findViewById(R.id.btn_spot);
        btn_deng=findViewById(R.id.btn_equal);
        btn_baifenhao=findViewById(R.id.btn_percent);
        btn1=findViewById(R.id.btn_num1);
        btn2=findViewById(R.id.btn_num2);
        btn3=findViewById(R.id.btn_num3);
        btn4=findViewById(R.id.btn_num4);
        btn5=findViewById(R.id.btn_num5);
        btn6=findViewById(R.id.btn_num6);
        btn7=findViewById(R.id.btn_num7);
        btn8=findViewById(R.id.btn_num8);
        btn9=findViewById(R.id.btn_num9);
        btn0=findViewById(R.id.btn_num0);

        //设置点击监听事件
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_cheng.setOnClickListener(this);
        btn_qk.setOnClickListener(this);
        btn_chu.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_dian.setOnClickListener(this);
        btn_tui.setOnClickListener(this);
        btn_deng.setOnClickListener(this);
        btn_baifenhao.setOnClickListener(this);
        etresult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String etinput=etresult.getText().toString();



        //获取内容转成String类型
        switch (v.getId()){
            case R.id.btn_num0:
            case R.id.btn_num1:
            case R.id.btn_num2:
            case R.id.btn_num3:
            case R.id.btn_num4:
            case R.id.btn_num5:
            case R.id.btn_num6:
            case R.id.btn_num7:
            case R.id.btn_num8:
            case R.id.btn_num9:
            case R.id.btn_spot:
                if(clear_flag){
                    clear_flag=false;
                    etinput="";
                    etresult.setText("");
                }
                etresult.setText(etinput+((Button)v).getText()); //点击数字和小数点直接显示内容
                break;
            case R.id.btn_pass:
            case R.id.btn_min:
            case R.id.btn_multiply:
            case R.id.btn_divide:
            case R.id.btn_percent:
                if(clear_flag){
                    clear_flag=false;
                    etinput="";
                    etresult.setText("");
                }
                etresult.setText(etinput+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_backspace:
                if(clear_flag){
                    clear_flag=false;
                    etinput="";
                    etresult.setText("");
                }else if(etinput!=null&&!etinput.equals("")){
                    etresult.setText(etinput.substring(0,etinput.length()-1)); //如果输入框内容不为空，则去掉最后一位
                }
                break;
            case R.id.btn_c:
                clear_flag=false;
                etinput="";
                etresult.setText(""); //直接设置输入框为空
                break;
            case R.id.btn_equal:
                getResult(); //点击等号调用getResult()方法
                break;
        }
    }

    private void getResult() {
        String exp=etresult.getText().toString(); //获取输入框的内容
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){  //判断输入框是否包含空格，也就是没有点击运算符
            return;
        }
        if(clear_flag){  //点击等号clear_flag为true，当再点击别的数字或运算符时才会变为false，如果连续点击等号，则第二次点击无效，直接返回
            clear_flag=false;
            return;
        }
        clear_flag=true;
        double result=0;
        String s1=exp.substring(0,exp.indexOf(" "));  //运算符前面的字符串
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2); //运算符，是根据运算符前边的空格计算的
        String s2=exp.substring(exp.indexOf(" ")+3);  //运算符后边的字符串
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);  //将字符串转换为double类型
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result=d1+d2;
            }else if(op.equals("-")){
                result=d1-d2;
            }else if(op.equals("*")){
                result=d1*d2;
            }else if(op.equals("%")){
                result=d1%d2;
            } else if(op.equals("/")){
                if(d2==0){ //判断除数为0的情况
                    result=0;
                }else{
                    result=d1/d2;
                }
            }

            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")){ //如果两个数都是整形，那么结果就需要显示为整数
                int r=(int)result;   //将String型计算结果强制转换为整形
                etresult.setText(r+"");
            }else{
                etresult.setText(result+"");
            }
        }else if(!s1.equals("")&&s2.equals("")){ //如果用户输入一个数字就点击运算符，那么将不计算
            etresult.setText(exp);
        }else if(s1.equals("")&&!s2.equals("")){ //如果一上来就点击运算符并输入第二个数，那么第一个数默认为0
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result=0+d2;
            }else if(op.equals("-")){
                result=0-d2;
            }else if(op.equals("*")){
                result=0;
            }else if(op.equals("/")){
                result=0;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")){
                int r=(int)result;
                etresult.setText(r+"");
            }else{
                etresult.setText(result+"");
            }
        }else{
            etresult.setText("");
        }
        }
    }
