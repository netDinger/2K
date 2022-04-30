package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.di.component.DiceBetComponentProvider;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.GridRecyclerView;
import com.dingar.twok.dice.presentation.contract.DiceBetContract;

import java.util.ArrayList;

import javax.inject.Inject;

public class Activity_DiceBet extends AppCompatActivity implements DiceBetContract.View {
    //private final String TAG = "Activity Dice Bet";
    @Inject
    public DiceBetContract.Presenter presenter;

    DiceBetComponent diceBetComponent;

    private GridRecyclerviewAdapter gridRecyclerviewAdapter;
    private TextView balance;

    private ArrayList<LotteryModel> lotteryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        balance = findViewById(R.id.balance);

        //initiate the login component and inject the view
        diceBetComponent = ((DiceBetComponentProvider)getApplicationContext())
                .provideDiceBetComponent();
        diceBetComponent.inject(this);
        presenter.setView(this);

        //recycler grid layout initiation
        GridRecyclerView gridRecyclerView = findViewById(R.id.betSlips);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerviewAdapter = new GridRecyclerviewAdapter(this);
        gridRecyclerView.setAdapter(gridRecyclerviewAdapter);

        //load available bet slips (not all because some are excluded by provider)
        presenter.loadLotteries();
        presenter.onLoadBalance();

        widgets();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onLotteriesLoad(ArrayList<LotteryModel> lotteries) {
        gridRecyclerviewAdapter.setData(lotteries);
        gridRecyclerviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addBetSlip(LotteryModel lotteryModel) {
        lotteryModels.add(lotteryModel);
    }

    @Override
    public void removeBetSlip(LotteryModel lotteryModel) {
        lotteryModels.remove(lotteryModel);
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
    }

    private void widgets(){
        lotteryModels = new ArrayList<>();
        Button bet = findViewById(R.id.bet);
        EditText amount = findViewById(R.id.amount);
        bet.setOnClickListener(v-> {
            if (presenter.isStringValid(amount.getText().toString())&&!lotteryModels.isEmpty()){
                //if amount is not empty and at least one lottery is selected
            Intent intent = new Intent();
            intent.putExtra("betSlips",lotteryModels);
            intent.putExtra("amount",amount.getText().toString().trim());
            intent.setClass(this,Activity_Bet_Slips.class);
            startActivity(intent);
            }
            else
                Toast.makeText(this,R.string.amount_warning,Toast.LENGTH_SHORT).show();
        });
    }
}