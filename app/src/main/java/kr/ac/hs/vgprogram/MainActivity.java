package kr.ac.hs.vgprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;



public class MainActivity extends AppCompatActivity  {
    private FirebaseAuth firebaseAuth;
    private static final int RC_SIGN_IN = 900;  //구글로그인 result 상수
    private GoogleSignInClient googleSignInClient;
    private SignInButton buttonGoogle;
    final private String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText login_id = findViewById(R.id.login_id);
        EditText login_password = findViewById(R.id.login_password);

        buttonGoogle = findViewById(R.id.btn_googleSignIn);
        // Google 로그인을 앱에 통합
        // GoogleSignInOptions 개체를 구성할 때 requestIdToken을 호출
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                Intent intent = new Intent(getApplicationContext(),activity_vgprog_content.class);
                startActivity(intent);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });






        Button login_button = findViewById(R.id.login_button);//로그인 버튼
        Button join_button = findViewById(R.id.join_button);//회원가입 버튼

        firebaseAuth = FirebaseAuth.getInstance();


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

        //회원가입 버튼 클릭할경우
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signup.class);
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
                            Intent intent = new Intent(MainActivity.this,activity_vgprog_content.class);
                            intent.putExtra("userid",id);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();


                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }


}







