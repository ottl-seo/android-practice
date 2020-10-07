package com.example.samplehi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText TextInputEditText_Email, TextInputEditText_Password;
    RelativeLayout RelativeLayout_login;
    String emailOK="123@gmail.com";
    String passwordOK="1234";

    String inputEmail=""; //사용자가 입력한 값 저장하는 전.역.변.수.
    String inputPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_Email=findViewById(R.id.TextInputEditText_Email);
        TextInputEditText_Password=findViewById(R.id.TextInputEditText_Password);
        RelativeLayout_login=findViewById(R.id.RelativeLayout_login);

        // 1. 값을 가져온다. - 검사 (123@gmail.com / 1234 인 경우에만)
        // 2. 클릭을 감지한다.
        // 3. 1번의 값을 다음 액티비티로 넘긴다.

        RelativeLayout_login.setClickable(false); //처음엔 누를수 없게.

        TextInputEditText_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { //바뀌기 전에

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //바뀔때
                Log.d("SENTI",s.toString());
                if(s!=null) {
                    inputEmail = s.toString(); //전역변수가 생겼으니 서로의 이메일,패스워드를 저장할 수 있게 되었습니다.
                    RelativeLayout_login.setEnabled((validation()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { //글자가 바뀌고 나서

            }
        });

        TextInputEditText_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { //바뀌기 전에

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //바뀔때

                Log.d("SENTI",s.toString());
                if(s!=null){
                    inputPassword=s.toString();
                    RelativeLayout_login.setEnabled((validation()));// 이 함수 결과에 따라 -true/false 값이 저장 - enabled 할지말지 결정
                    }
                }



            @Override
            public void afterTextChanged(Editable s) { //글자가 바뀌고 나서

            }
        });


//위 두개(email/password)가 맞으면~ onclick 이 가능하게 해주는 것임.

//        RelativeLayout_login.setClickable(true); //이건 지워주기(항상 클릭 가능하지 않게!)
// setClickable- 클릭 가능여부 설정 / setOnClickListener- 클릭 리스너 설정
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=TextInputEditText_Email.getText().toString(); //어떤걸 가져오는 함수 get()
                String password=TextInputEditText_Password.getText().toString();

                Intent intent; // (현재 액티비티.this, 이동할 액티비티.class)
                intent = new Intent(MainActivity.this,LoginResultActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("password",password); //값 넣기- putExtra()
                startActivity(intent); ///이 네 줄로 인해서 우리가 받아온 값을 들고~ LoginResultActivity로 가는것임

            }
        });


            }
    public boolean validation(){
        Log.d("SENTI",inputEmail+"/"+inputPassword+"/"+(inputEmail.equals(emailOK))+"/"+(inputPassword.equals(passwordOK)));
        return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK); //이 조건이 다 부합하는지에 대한 결과를 돌려주는 함수
    }
}