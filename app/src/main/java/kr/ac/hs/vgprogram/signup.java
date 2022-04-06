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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    TextView text;
    private FirebaseAuth mfirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText signNameTxet;
    private EditText signIdText;
    private EditText editTextTextEmailAddress;
    private EditText signPWText;
    private EditText signPWCheckText;
    private Button button2;
    private Button signFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mfirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("vgprogram");


        signIdText = (EditText) findViewById(R.id.signIdText);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        signNameTxet = (EditText) findViewById(R.id.signNameTxet);
        signPWText = (EditText) findViewById(R.id.signPWText);
        signPWCheckText = (EditText) findViewById(R.id.signPWCheckText);
        signFinish = findViewById(R.id.signFinish);

        signFinish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            // 회원가입 처리 시작
                String strEmail = editTextTextEmailAddress.getText().toString();
                String strPwd = signPWText.getText().toString();
                String strName = signNameTxet.getText().toString();
                String strId = signIdText.getText().toString();
                String strPwdCheck = signPWCheckText.getText().toString();

                //파이어베이스 진행
                mfirebaseAuth.createUserWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mfirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmail(firebaseUser.getEmail());
                            account.setPwd(strPwd);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(signup.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();}
                        else {
                            Toast.makeText(signup.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }
        });

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


    }


}
