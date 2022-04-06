package kr.ac.hs.vgprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        EditText login_id  = findViewById(R.id.login_id);
        EditText login_password  = findViewById(R.id.login_password);



        Button login_button = findViewById(R.id.login_button);//로그인 버튼
        Button join_button = findViewById(R.id.join_button);//회원가입 버튼
        Button id_button = findViewById(R.id.id_button);//아이디찾기 버튼
        Button passwd_button = findViewById(R.id.passwd_button);//비밀번호찾기 버튼

        //로그인 버튼 클릭할 경우
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!login_id.getText().toString().equals("")&&
                !login_password.getText().toString().equals("")){
                    loginUser(login_id.getText().toString(),
                            login_password.getText().toString());
                }else {
                    Toast.makeText(MainActivity.this, "아이디와 비밀번호를 입력하세요.",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                }
            }
        };


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
                Intent intent = new Intent(getApplicationContext(),idfind.class);
                startActivity(intent);

            }
        });

        //비밀번호찾기 버튼 클릭할경우
        passwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),pwfind.class);
                startActivity(intent);

            }
        });

    }

    private void loginUser(String id, String password) {
        firebaseAuth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            firebaseAuth.addAuthStateListener(firebaseAuthListener);
                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}