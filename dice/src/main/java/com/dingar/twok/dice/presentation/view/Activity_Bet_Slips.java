package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.ParseException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.di.component.BetSlipComponent;
import com.dingar.twok.dice.di.component.BetSlipComponentProvider;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.contract.BetListContract;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

public class Activity_Bet_Slips extends AppCompatActivity implements BetListContract.View {

    /**
     * to add arraylist of lottery form {@link Activity_DiceBet}
     * with no amount (default is 0)
     */
    ArrayList<LotteryModel> lotteryModels;
    /**
     * list of lotteries with amount
     */
    ArrayList<LotteryModel> betList;
    String amount;
    private TextView balance,totalBet;

    private BetListRecyclerviewAdapter betListRecyclerviewAdapter;

    @Inject
    public BetListContract.Presenter presenter;

    BetSlipComponent betSlipComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_slips);
        addToolbar();
        RecyclerView betsList = findViewById(R.id.betSlips);
        balance = findViewById(R.id.balance);
        totalBet = findViewById(R.id.total);
        betsList.setHasFixedSize(true);
        betList = new ArrayList<>();

        //initiate the component
        betSlipComponent = ((BetSlipComponentProvider) getApplicationContext()).provideBetSlipComponent();
        betSlipComponent.inject(this);

        presenter.setView(this);

        //initiate lotteryModels
        lotteryModels = new ArrayList<>();
        lotteryModels = (ArrayList<LotteryModel>) getIntent().getSerializableExtra("betSlips");
        amount = getIntent().getStringExtra("amount");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        betsList.setLayoutManager(layoutManager);
        betListRecyclerviewAdapter = new BetListRecyclerviewAdapter(betList);
        betsList.setAdapter(betListRecyclerviewAdapter);
        betSlipComponent.inject(betListRecyclerviewAdapter);

        this.setData();

        findViewById(R.id.bet).setOnClickListener(view -> presenter.onBetClick());

        presenter.onLoadBalance();
    }

    @Override
    public void onBettingSuccess() {
        Toast.makeText(this, "Betted Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onTotalAmountCalculated(int totalAmount) {
        totalBet.setText(String.valueOf(totalAmount));
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
    }

    /**
     * method to add amount to each model and add it to new {@link Activity_Bet_Slips#betList}
     */
    @SuppressLint("NotifyDataSetChanged")
    private void setData() {
        for (LotteryModel lotteryModel : lotteryModels) {
            try {
                lotteryModel.setAmount(Integer.parseInt(amount));
                betList.add(lotteryModel);
            } catch (ParseException|NumberFormatException e) {
                e.printStackTrace();
            }
        }

        presenter.setBetList(betList);
        presenter.calculateTotalAmount();
        betListRecyclerviewAdapter.addData(betList);
        betListRecyclerviewAdapter.notifyDataSetChanged();

    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

}