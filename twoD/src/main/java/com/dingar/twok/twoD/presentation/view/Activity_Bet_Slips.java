package com.dingar.twok.twoD.presentation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.twoD.R;
import com.dingar.twok.twoD.data.model.LotteryModel;
import com.dingar.twok.twoD.di.component.ComponentProviderTwoD;
import com.dingar.twok.twoD.di.component.TwoDBetSlipComponent;
import com.dingar.twok.twoD.presentation.contract.BetListContract;

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
    private String amount;
    private long winDate;

    private TextView balance,totalBet;
    private ImageView addNewBet;
    private EditText lotteryNumber,Amount;

    private AlertDialog newBetDialog;

    private RecyclerView betsList;
    private BetListRecyclerviewAdapter betListRecyclerviewAdapter;

    @Inject
    public BetListContract.Presenter presenter;

    TwoDBetSlipComponent twoDBetSlipComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2d_bet_slips);

        //initiate the component
        twoDBetSlipComponent = ((ComponentProviderTwoD) getApplicationContext()).provideTwoDBetSlipComponent();
        twoDBetSlipComponent.inject(this);

        widget();
        initiate();
    }

    @Override
    public void onBettingSuccess() {
        Toast.makeText(this, "Betted Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onTotalAmountCalculated(double totalAmount) {
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
                lotteryModel.setWinDate(winDate);
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
        addNewBet = findViewById(R.id.add);

        findViewById(R.id.bet).setOnClickListener(view -> presenter.onBetClick());
        betsList.setHasFixedSize(true);
        presenter.setView(this);

        addNewBet.setOnClickListener(view->{
            createAddBetDialog();
        });
    }

    private void initiate(){
        //initiate lotteryModels
        lotteryModels = new ArrayList<>();
        lotteryModels = (ArrayList<LotteryModel>) getIntent().getSerializableExtra("betSlips");
        amount = getIntent().getStringExtra("amount");
        winDate = getIntent().getLongExtra("winDate",0L);
        betList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        betsList.setLayoutManager(layoutManager);
        betListRecyclerviewAdapter = new BetListRecyclerviewAdapter(betList);
        betsList.setAdapter(betListRecyclerviewAdapter);
        //inject RecyclerView Adapter
        twoDBetSlipComponent.inject(betListRecyclerviewAdapter);

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

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    //dialog to show add new bet option
    private void createAddBetDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View addBetView = inflater.inflate(R.layout.item_add_bet_slip,null);
        lotteryNumber = addBetView.findViewById(R.id.lottery_number);
        Amount = addBetView.findViewById(R.id.amount);
        builder.setView(addBetView);
        builder.setCancelable(true);
        builder.setTitle(R.string.add_bet);
        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
            if (!lotteryNumber.getText().toString().isEmpty()&&!Amount.getText().toString().isEmpty()){
                betList.add(new LotteryModel(lotteryNumber.getText().toString(),
                        Integer.parseInt(Amount.getText().toString())));
                presenter.setBetList(betList);
                betListRecyclerviewAdapter.notifyItemInserted(betList.size()-1);
                presenter.onAmountChanged(betList.size()-1,Amount.getText().toString());
                newBetDialog.dismiss();
            }
            else showToast(getString(R.string.lottery_warning));
        });
        newBetDialog = builder.create();
        newBetDialog.show();
    }

    @Override
    public void onBackPressed() {
        newBetDialog = null;
        finish();
    }

    @Override
    protected void onDestroy() {
        newBetDialog = null;
        super.onDestroy();
    }
}