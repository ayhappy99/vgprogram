package kr.ac.hs.vgprogram;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class recipe extends AppCompatActivity {

    final private String TaG = getClass().getSimpleName();

    ListView recipeListView;
    Button recipewrite;

    String login_id="";


    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> seqList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        login_id=getIntent().getStringExtra("login_id");


        recipewrite = findViewById(R.id.recipewrite);

        recipewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recipe.this,recipe_write.class);
                intent.putExtra("login_id",login_id);
                startActivity(intent);

            }
        });




//리스트

        recipeListView = findViewById(R.id.recipeListView);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(recipe.this,recipe_detail.class);
                intent.putExtra("board_seq",seqList.get(i));
                intent.putExtra("login_id",login_id);
                startActivity(intent);

            }
        });

        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(recipe.this,android.R.layout.simple_list_item_1,titleList);
        recipeListView.setAdapter(listadapter);

        listadapter.notifyDataSetChanged();








        //스피너
        Spinner typeSpinner = findViewById(R.id.typeSpinner);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);*/

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }









}
