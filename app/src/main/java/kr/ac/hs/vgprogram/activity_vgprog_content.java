package kr.ac.hs.vgprogram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import me.relex.circleindicator.CircleIndicator3;

public class activity_vgprog_content extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;


    private DrawerLayout mDrawerLayout;
    private Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vgprog_content);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_48);




        ImageButton beef = findViewById(R.id.beefbtn);
        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),beefbtn.class);
                startActivity(intent);

            }
        });

        ImageButton chick = findViewById(R.id.chickbtn);
        chick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),chickbtn.class);
                startActivity(intent);

            }
        });

        ImageButton eggg = findViewById(R.id.egggbtn);
        eggg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),eggbtn.class);
                startActivity(intent);

            }
        });

        ImageButton fish = findViewById(R.id.fishbtn);
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),fishbtn.class);
                startActivity(intent);

            }
        });

        ImageButton milk = findViewById(R.id.milkbtn);
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),milkbtn.class);
                startActivity(intent);

            }
        });

        ImageButton vege = findViewById(R.id.vegebtn);
        vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),vegebtn.class);
                startActivity(intent);

            }
        });




       // ActionBar actionBar = getSupportActionBar();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = item.getItemId();
                String title = item.getTitle().toString();

                if (id == R.id.menu_item1) {
                    Intent intent = new Intent(getApplicationContext(), foodfd.class);
                    startActivity(intent);

                } else if (id == R.id.menu_item2) {
                    Intent intent = new Intent(getApplicationContext(), recipe.class);
                    startActivity(intent);
                } else if (id == R.id.menu_item3) {
                    Intent intent = new Intent(getApplicationContext(), mypage.class);
                    startActivity(intent);
                } else if (id == R.id.menu_item4) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }

                return true;

            }
        });
        //ViewPager2
        mPager = findViewById(R.id.viewpager);
//Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
//Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);
//ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        mPager.setCurrentItem(1000); //?????? ??????
        mPager.setOffscreenPageLimit(3); //?????? ????????? ???
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });






    }





    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId()){
            case android.R.id.home:{
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




}