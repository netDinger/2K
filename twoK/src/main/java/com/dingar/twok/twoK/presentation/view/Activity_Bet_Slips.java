package com.dingar.twok.twoK.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.ParseException;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.dingar.twok.twoK.R;
import com.dingar.twok.twoK.data.model.LotteryModel;
import com.dingar.twok.twoK.di.component.ComponentProviderTwoK;
import com.dingar.twok.twoK.di.component.TwoKBetSlipComponent;
import com.dingar.twok.twoK.presentation.contract.BetListContract;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

/**
 * show the user selected bet slips,
 * to bet all the bet slip selected by user
 */
public class Activity_Bet_Slips extends AppCompatActivity implements BetListContract.View {

    //TODO: change betList add amount from previous activity
    //remove a arraylist later
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
    private ImageView addNewBet;
    private EditText lotteryNumber,Amount;

    private RecyclerView betsList;
    private BetListRecyclerviewAdapter betListRecyclerviewAdapter;

    @Inject
    public BetListContract.Presenter presenter;

    TwoKBetSlipComponent twoKBetSlipComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twok_bet_slips);

        //initiate the component
        twoKBetSlipComponent = ((ComponentProviderTwoK) getApplicationContext()).provideTwoKBetSlipComponent();
        twoKBetSlipComponent.inject(this);

        widget();
        initiate();
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

    private void widget(){
        addToolbar();
        betsList = findViewById(R.id.betSlips);
        balance = findViewById(R.id.balance);
        totalBet = findViewById(R.id.total);
        LinearLayout v = findViewById(R.id.addBetSlip);
        addNewBet = v.findViewById(R.id.add);
        lotteryNumber = v.findViewById(R.id.lottery_number);
        Amount = v.findViewById(R.id.amount);

        findViewById(R.id.bet).setOnClickListener(view -> presenter.onBetClick());
        betsList.setHasFixedSize(true);
        presenter.setView(this);

        addNewBet.setOnClickListener(view->{
            betList.add(new LotteryModel(lotteryNumber.getText().toString(),
                    Integer.parseInt(Amount.getText().toString())));
            presenter.setBetList(betList);
            betListRecyclerviewAdapter.notifyItemInserted(betList.size()-1);
            presenter.onAmountChanged(betList.size()-1,Amount.getText().toString());
        });
    }

    private void initiate(){
        //initiate lotteryModels
        lotteryModels = new ArrayList<>();
        lotteryModels = (ArrayList<LotteryModel>) getIntent().getSerializableExtra("betSlips");
        amount = getIntent().getStringExtra("amount");
        betList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        betsList.setLayoutManager(layoutManager);
        betListRecyclerviewAdapter = new BetListRecyclerviewAdapter(betList);
        betsList.setAdapter(betListRecyclerviewAdapter);
        //inject RecyclerView Adapter
        twoKBetSlipComponent.inject(betListRecyclerviewAdapter);

        this.setData();
        presenter.onLoadBalance();
    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); }
}