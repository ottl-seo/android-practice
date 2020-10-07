package com.example.samplehi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {

    TextView TextView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_get=findViewById(R.id.TextView_get); // "Textview_get의 리소스는 여기서 찾아라" => findViewById(R.id.TextView_get);

        Intent intent = getIntent(); //받을때 - getIntent()
        Bundle bundle = intent.getExtras(); // MainActivity에서 putExtra()로 넣었으니 getExtras()로 받고
        String email = bundle.getString("email"); //getString() 으로 이름으로 직접 가져오기
        String password = bundle.getString("password");

        TextView_get.setText(email + "/" + password); //이렇게 하는 이유? 안에다 string을 넣어야 하는데 구분해서 보여주려공

    }
}
