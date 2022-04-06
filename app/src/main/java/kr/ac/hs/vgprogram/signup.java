package kr.ac.hs.vgprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;



import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class signup extends AppCompatActivity {
    TextView text;
    private FirebaseAuth firebaseAuth;
    private EditText signNameTxet;
    private EditText signIdText;
    private EditText editTextTextEmailAddress;
    private EditText signPWText;
    private EditText signPWCheckText;
    private Button button2;
    private Button signFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firebaseAuth = FirebaseAuth.getInstance();

        signIdText = (EditText) findViewById(R.id.signIdText);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        signNameTxet = (EditText) findViewById(R.id.signNameTxet);
        signPWText = (EditText) findViewById(R.id.signPWText);
        signPWCheckText = (EditText) findViewById(R.id.signPWCheckText);

        //비밀번호 일치 확인 버튼
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (signPWText.getText().toString().equals(signPWCheckText.getText().toString())) {
                    Toast.makeText(signup.this, "일치합니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(signup.this, "일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }

        });


        //회원가입 완료 버튼 클릭
        Button signFinish = (Button)findViewById(R.id.signFinish);
        signFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if
                (!signNameTxet.getText().toString().equals("") && !signIdText.getText().toString().equals("") && !editTextTextEmailAddress.getText().toString().equals("") && !signPWText.getText().toString().equals("")) {
                    if (signPWText.getText().toString().equals(signPWCheckText.getText().toString())) {
                        createUser(signIdText.getText().toString(), signNameTxet.getText().toString(), editTextTextEmailAddress.getText().toString(), signPWText.getText().toString());
                    } else {
                        Toast.makeText(signup.this, "비밀번호가 일치 확인하시오.", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(signup.this, "공백을 확인하시오.", Toast.LENGTH_LONG).show();
                }
            }
        });


        text = (TextView) findViewById(R.id.back);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView 클릭될 시 할 코드작성
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void createUser(String id,String name,String email,String pw) {
        firebaseAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this, "회원가입 성공.", Toast.LENGTH_LONG).show();
                            FirebaseUser user= firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(signup.this, "이미 존재하는 계정입니다..", Toast.LENGTH_LONG).show();
                            return;
                        }

                    }
                });
    }


}
