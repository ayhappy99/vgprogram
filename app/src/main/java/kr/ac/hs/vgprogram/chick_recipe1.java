package kr.ac.hs.vgprogram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class chick_recipe1 extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    String email = user.getEmail();
    private Button chick_comment_button;
    private EditText chick_comment_content;
    private DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManger;
    private List<commentData> commentList;
    private RecyclerView.Adapter mAdapter;
    private Scroller sc;
    private DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chick_recipe1);

        chick_comment_button=findViewById(R.id.Button_send);
        chick_comment_content=findViewById(R.id.EditText_chat);

        chick_comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=chick_comment_content.getText().toString();

                if(msg!=null){
                    commentData com = new commentData();
                    com.setMsg(msg);
                    com.setNicname(email);
                    myRef.push().setValue(com);
                }
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManger);

        commentList = new ArrayList<>();
        mAdapter = new commentAdapter(commentList, chick_recipe1.this, email);

        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase database =FirebaseDatabase.getInstance();
        myRef = database.getReference("chick_comment1");
//
////        commentData comD=new commentData();
////        comD.setNicname(email);
////        comD.setMsg("hi");
////        myRef.setValue(comD);



        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s){
                Log.i("chick_comment1",dataSnapshot.getValue().toString());

                commentData chick_comment1 = dataSnapshot.getValue(commentData.class);
                ((commentAdapter) mAdapter).addCom(chick_comment1);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s){

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot){

            }


            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}