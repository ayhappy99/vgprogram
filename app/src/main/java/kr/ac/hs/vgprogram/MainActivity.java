package kr.ac.hs.vgprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login_button = findViewById(R.id.login_button);//로그인 버튼
        Button join_button = findViewById(R.id.join_button);//회원가입 버튼
        Button id_button = findViewById(R.id.id_button);//아이디찾기 버튼
        Button passwd_button = findViewById(R.id.passwd_button);//비밀번호찾기 버튼

        //로그인 버튼 클릭할 경우
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //회원가입 버튼 클릭할경우
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signup.class);
                startActivity(intent);

            }
        });

        //아이디찾기 버튼 클릭할경우
        id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //비밀번호찾기 버튼 클릭할경우
        passwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}