package kr.ac.hs.vgprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Room.AppDatabase;
import Room.User;

public class DetailActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    private EditText detailTitle;
    private ImageView detailImage;
    private EditText detailDes;
    private AppDatabase db;

    private FloatingActionButton exit;
    private FloatingActionButton update;

    private int id;
    private String title;
    private String des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialized();


        exit.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), recipe.class);
            startActivity(intent);
        });
    }
    private void initialized() {
        //update = findViewById(R.id.update);
        exit = findViewById(R.id.exit);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailDes = findViewById(R.id.detailDes);
        db = AppDatabase.getInstance(this);

        User detail = getIntent().getParcelableExtra("data");

        id = detail.getId();
        title = detail.getTitle();
        des = detail.getDes();

        detailTitle.setText(title);
        detailDes.setText(des);
    }

}