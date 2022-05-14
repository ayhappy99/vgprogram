package kr.ac.hs.vgprogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


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
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("member");


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        signIdText = (EditText) findViewById(R.id.signIdText);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        signNameTxet = (EditText) findViewById(R.id.signNameTxet);
        signPWText = (EditText) findViewById(R.id.signPWText);
        signPWCheckText = (EditText) findViewById(R.id.signPWCheckText);
        signFinish = findViewById(R.id.signFinish);
        final CheckBox admin_checkBox=(CheckBox)findViewById(R.id.admin_checkBox);

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
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String resultText = "";
                        if (admin_checkBox.isChecked() == true) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseAdmin = mfirebaseAuth.getCurrentUser();
                                UserAccount adminaccount = new UserAccount();
                                adminaccount.setIdToken(firebaseAdmin.getUid());
                                adminaccount.setEmail(firebaseAdmin.getEmail());
                                adminaccount.setPwd(strPwd);

                                mDatabaseRef.child("adminAccount").child(firebaseAdmin.getUid()).setValue(adminaccount);
                                Toast.makeText(signup.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(signup.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mfirebaseAuth.getCurrentUser();
                                UserAccount useraccount = new UserAccount();
                                useraccount.setIdToken(firebaseUser.getUid());
                                useraccount.setEmail(firebaseUser.getEmail());
                                useraccount.setPwd(strPwd);

                                mDatabaseRef.child("adminAccount").child(firebaseUser.getUid()).setValue(useraccount);
                                Toast.makeText(signup.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(signup.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                            }
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


        //회원가입 완료 버튼 클릭
        Button signFinish = (Button)findViewById(R.id.signFinish);
        signFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextTextEmailAddress.getText().toString().trim();
                String uid = signIdText.getText().toString().trim();
                String name = signNameTxet.getText().toString().trim();
                String pwd = signPWText.getText().toString().trim();
                String pwdcheck = signPWCheckText.getText().toString().trim();

                if(pwd.equals(pwdcheck)){
                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (admin_checkBox.isChecked() == true) {
                                if (task.isSuccessful()) {

                                    FirebaseUser admin = firebaseAuth.getCurrentUser();
                                    UserAccount adminaccount = new UserAccount();
                                    String email = admin.getEmail();
                                    String uid = admin.getUid();
                                    String name = signNameTxet.getText().toString().trim();
                                    String adpwd = signPWText.getText().toString().trim();


                                    HashMap<Object, String> hashMap = new HashMap<>();

                                    hashMap.put("adminid", uid);
                                    hashMap.put("adminemail", email);
                                    hashMap.put("adminname", name);
                                    hashMap.put("adpwd", adpwd);


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference DatabaseRef = database.getReference("member");
                                    DatabaseRef.child("AdminAccount").child(uid).setValue(hashMap);


                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(signup.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(signup.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            else{
                                if (task.isSuccessful()) {

                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    UserAccount useraccount = new UserAccount();
                                    String email = user.getEmail();
                                    String uid = user.getUid();
                                    String name = signNameTxet.getText().toString().trim();
                                    String pwd = signPWText.getText().toString().trim();


                                    HashMap<Object, String> hashMap = new HashMap<>();

                                    hashMap.put("userid", uid);
                                    hashMap.put("useremail", email);
                                    hashMap.put("username", name);
                                    hashMap.put("userpwd", pwd);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference DatabaseRef = database.getReference("member");
                                    DatabaseRef.child("UserAccount").child(uid).setValue(hashMap);


                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(signup.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(signup.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        }
                    });


                }else{
                    Toast.makeText(signup.this, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;

                }


            }
        });



    }
}