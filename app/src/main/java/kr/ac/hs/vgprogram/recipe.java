package kr.ac.hs.vgprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class recipe extends AppCompatActivity {
    //private Spinner typeSpinner;
    TextView textView;

    private ListView recipeListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
//리스트

        recipeListView = findViewById(R.id.recipeListView);
        List<String> data = new ArrayList<>();

        ArrayAdapter<String> listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        recipeListView.setAdapter(listadapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //스피너
        Spinner typeSpinner = findViewById(R.id.typeSpinner);

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
