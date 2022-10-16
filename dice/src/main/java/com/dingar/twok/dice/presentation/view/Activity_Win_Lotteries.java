package com.dingar.twok.dice.presentation.view;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.di.component.ComponentProviderDice;
import com.dingar.twok.dice.di.component.DiceWinLotteryComponent;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import java.util.Objects;

import javax.inject.Inject;

/**
 * show the win lottery history
 * @see WinLotteryContract for better understanding
 */
public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    private final String TAG = "Activity_Win_Lotteries";

    @Inject
    public WinLotteryContract.Presenter presenter;

    DiceWinLotteryComponent component;

    WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter;

    private RecyclerView winHistoryRecyclerview;
    private TextView updated_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lotteries);

        //initiate component and inject the view
        component = ((ComponentProviderDice)getApplicationContext()).provideWinLotteryComponent();
        component.inject(this); //inject the view

        widgets();
        initiate();
    }

    @Override
    public void onLuckyHistoryLoaded(WinLotteryModel model) {
        showToast(model.getLucky_number());
        winLotteryRecyclerViewAdapter.addData(model);
    }


    @Override
    public void onCurrentTwoDLoaded() {
        try {
            String s = "Updated At: "+DateUtil.timeStampToDate(String.valueOf(System.currentTimeMillis()));
            updated_date.setText(s);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
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

    @Override public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}