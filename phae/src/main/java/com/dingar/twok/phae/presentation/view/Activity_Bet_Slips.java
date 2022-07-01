package com.dingar.twok.phae.presentation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.phae.R;
import com.dingar.twok.phae.data.model.LotteryModel;
import com.dingar.twok.phae.di.component.ComponentProviderPhae;
import com.dingar.twok.phae.di.component.PhaeBetSlipComponent;
import com.dingar.twok.phae.presentation.contract.BetListContract;

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
    long winDate;

    private TextView balance,point,totalBet;
    private ImageView addNewBet;
    private EditText lotteryNumber,Amount;
    private Button bet;

    private RecyclerView betsList;
    private BetListRecyclerviewAdapter betListRecyclerviewAdapter;

    private AlertDialog newBetDialog;

    @Inject
    public BetListContract.Presenter presenter;

    PhaeBetSlipComponent phaeBetSlipComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phae_bet_slips);

        //initiate the component
        phaeBetSlipComponent = ((ComponentProviderPhae) getApplicationContext()).providePhaeBetSlipComponent();
        phaeBetSlipComponent.inject(this);

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
        this.balance.setText(getString(R.string.balance)+balance);
    }

    @Override
    public void onPointLoaded(String point) {
        this.point.setText(point);
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
        point = findViewById(R.id.point);
        totalBet = findViewById(R.id.total);
        addNewBet = findViewById(R.id.add);

        bet = findViewById(R.id.bet);
        bet.setOnClickListener(view -> showBetOptions(bet));
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
        phaeBetSlipComponent.inject(betListRecyclerviewAdapter);

        this.setData();
        presenter.onLoadBalance();
        presenter.loadPoint();
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

    //to show bet menu to bet with point or balance
    private void showBetOptions(View anchor){
        PopupMenu popupMenu= new PopupMenu(this,anchor);
        popupMenu.getMenuInflater().inflate(R.menu.betmenu,popupMenu.getMenu());
        //registering popup with OnMenuItemClickListener
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.betWithBalance)
                presenter.onBetWithBalance();
            else
                presenter.onBetWithPoint();
            return true;
        });
        popupMenu.show();
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