package com.dingar.twok.lottery.presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.dingar.twok.lottery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Activity_Lottery_Main extends AppCompatActivity {

    private ViewPager2 viewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_main);
        initiate();
    }

    private void initiate() {
        BottomNavigationView tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //set previous Menu Item to 1(Lottery tab) and show from fragment Lottery
        prevMenuItem = tabLayout.getMenu().getItem(1);
        tabLayout.setSelectedItemId(R.id.lottery_tab);

        //setting up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        //set the view pager current item on tab selected
        tabLayout.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.history_tab) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.lottery_tab) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.account_tab) {
                viewPager.setCurrentItem(2);
            }
            return false;
        });

        setupViewPager(viewPager);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //set tab icon checked on viewpager scroll or selected
                prevMenuItem.setChecked(false); //previous menu item tab won't be selected anymore
                tabLayout.getMenu().getItem(position).setChecked(true);
                prevMenuItem = tabLayout.getMenu().getItem(position);
            }
        });
    }

    public void setupViewPager(@NonNull ViewPager2 viewPager){
        //connect fragment with viewPager for tab layout
        ViewPagerAdapter viewPagerAdapter =
               new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(new Fragment_History());
        viewPagerAdapter.addFragment(new Fragment_Lottery());
        viewPagerAdapter.addFragment(new Fragment_Setting());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1)
        moveTaskToBack(true);
        else
            viewPager.setCurrentItem(1);
    }
}