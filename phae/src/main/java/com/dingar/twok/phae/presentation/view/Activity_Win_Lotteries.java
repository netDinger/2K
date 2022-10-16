package com.dingar.twok.phae.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.phae.R;
import com.dingar.twok.phae.data.model.WinLotteryModel;
import com.dingar.twok.phae.di.component.ComponentProviderPhae;
import com.dingar.twok.phae.di.component.PhaeWinLotteryComponent;
import com.dingar.twok.phae.presentation.contract.WinLotteryContract;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

/**
 * show the win lottery history
 * @see WinLotteryContract for better understanding
 */
public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    PhaeWinLotteryComponent component;

    WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter;

    private RecyclerView winHistoryRecyclerview;
    private TextView updated_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phae_win_lotteries);

        //initiate component and inject the view
        component = ((ComponentProviderPhae)getApplicationContext()).providePhaeWinLotteryComponent();
        component.inject(this); //inject the view

        widgets();
        initiate();
    }

    @Override public void onLuckyHistoryLoaded(WinLotteryModel model) {
        winLotteryRecyclerViewAdapter.addData(model);
    }

    @Override
    public void onCurrentTwoDLoaded() {
        try {
            String s = "Updated At: "+DateUtil.timeStampToDate(String.valueOf(System.currentTimeMillis()));
            updated_date.setText(s);
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
    }

    private void widgets(){
        addToolbar();
        winHistoryRecyclerview = findViewById(R.id.winHistory);
        //current TwoD result

        updated_date = findViewById(R.id.updateDate);
    }

    private void initiate(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        winHistoryRecyclerview.setLayoutManager(layoutManager);
        winLotteryRecyclerViewAdapter =
                new WinLotteryRecyclerViewAdapter();
        winHistoryRecyclerview.setAdapter(winLotteryRecyclerViewAdapter);

        presenter.setView(this);
        //Tell presenter to load all lucky number within a month
        presenter.loadLuckyHistory();
    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); }

}