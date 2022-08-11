package com.dingar.twok.twoK.presentation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.core.ui.GridRecyclerView;
import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.twoK.R;
import com.dingar.twok.twoK.data.model.LotteryModel;
import com.dingar.twok.twoK.di.component.ComponentProviderTwoK;
import com.dingar.twok.twoK.di.component.TwoKBetComponent;
import com.dingar.twok.twoK.presentation.contract.DiceBetContract;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

/**
 * show the available (bet able) lottery list to user
 * @see DiceBetContract for better understanding
 */
public class Activity_DiceBet extends AppCompatActivity implements DiceBetContract.View {
    private final String TAG = "Activity Dice Bet";

    @Inject
    public DiceBetContract.Presenter presenter;

    TwoKBetComponent twoKBetComponent;

    private GridRecyclerviewAdapter gridRecyclerviewAdapter;
    private TextView time_remaining,quick_chooser, topping;
    private EditText amount;

    private AlertDialog winDateDialog; //to show the available win date
    private AlertDialog toppingDialog;

    private ArrayList<LotteryModel> lotteryModels;  //contains user selected lotteries
    private ArrayList<String> toppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twok_bet);

        //initiate the login component and inject the view
        twoKBetComponent = ((ComponentProviderTwoK)getApplicationContext())
                .provideTwoKBetComponent();
        twoKBetComponent.inject(this);
        presenter.setView(this);

        //recycler grid layout initiation
        GridRecyclerView gridRecyclerView = findViewById(R.id.betSlips);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerviewAdapter = new GridRecyclerviewAdapter(this);
        gridRecyclerView.setAdapter(gridRecyclerviewAdapter);

        widgets();
        initiate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initiate();
    }

    @Override
    public void onBackPressed() {
        presenter.dropView();
        winDateDialog = null;
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
    public void setTimeRemaining(String timeRemaining) {
        time_remaining.setText(timeRemaining);
    }

    @Override
    public void onBetAbleTimeLoaded(ArrayList<String> dates) {
        winDatePicker(dates);
    }

    @Override
    public void onToppingLoaded(ArrayList<String> toppings) {
        this.toppingList = toppings;
    }

    private void initiate(){
        //load available bet slips (not all because some are excluded by provider)
        presenter.loadLotteries("A");   //load lottery
        presenter.loadBetableTime();
        presenter.loadTimeRemaining();
        presenter.loadToppings();
    }

    private void widgets(){
        addToolbar();
        lotteryModels = new ArrayList<>();
        toppingList = new ArrayList<>();
        Button bet = findViewById(R.id.bet);
        ImageView history = findViewById(R.id.history);
        quick_chooser = findViewById(R.id.quick_choose);
        topping = findViewById(R.id.topping);
        amount = findViewById(R.id.amount);
        time_remaining = findViewById(R.id.time_remaining);
        ImageView help = findViewById(R.id.help);
        bet.setOnClickListener(v-> {
            if (presenter.isStringValid(amount.getText().toString())){ //if amount is not empty
                if (!lotteryModels.isEmpty()) {  // if at least one lottery is selected
                    if (winDateDialog != null) {
                        winDateDialog.show();
                    } else
                        Toast.makeText(this, R.string.wait, Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(this,R.string.lottery_warning,Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this,R.string.amount_warning,Toast.LENGTH_SHORT).show();
        });

        //navigate to lucky number history
        history.setOnClickListener(view -> startActivity(new Intent(this,Activity_Win_Lotteries.class)));

        help.setOnClickListener(view -> startActivity(new Intent(this,Activity_Help.class)));

        topping.setOnClickListener(view->{
            if (toppingList != null && !toppingList.isEmpty()) {
                NumberPicker picker = new NumberPicker(this);
                picker.setMinValue(0);
                picker.setMaxValue(toppingList.size() - 1);
                picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                picker.setDisplayedValues(toppingList.toArray(new String[0]));

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.select_topping);
                builder.setView(picker);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.select, (dialogInterface, i) -> {
                    toppingDialog.dismiss();
                    //show selected prefix(topping) in textview
                    String placeHolder = toppingList.get(picker.getValue())+getString(R.string.topping);
                   topping.setText(placeHolder);
                   //load the lotteries with selected prefix(topping)
                   presenter.loadLotteries(toppingList.get(picker.getValue()));
                });
            toppingDialog = builder.create();
            toppingDialog.show();
            }
        }); //topping onclick

        quick_chooser.setOnClickListener(view ->{
            Log.e("size",toppingList.size()+"");
            toppingList.add("00");
        });

    }//widgets

    /**
     *
     * @param dates list of win dates
     * called by {@link #onBetAbleTimeLoaded(ArrayList)}
     */
    private void winDatePicker(ArrayList<String> dates){
        try {
            //Create a custom number picker dialog to show the bet able win dates
            NumberPicker picker = new NumberPicker(this);
            picker.setMinValue(0);
            picker.setMaxValue(dates.size() - 1);
            picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            picker.setDisplayedValues(dates.toArray(new String[0]));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.select_win_date);
            builder.setView(picker);
            builder.setCancelable(true);
            builder.setPositiveButton(R.string.select, (dialogInterface, i) -> {
                winDateDialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("betSlips",lotteryModels);
                intent.putExtra("amount",amount.getText().toString().trim());
                try {
                    intent.putExtra("winDate", DateUtil.dateToTimestamp(dates.get(picker.getValue())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.setClass(this,Activity_Bet_Slips.class);
                winDateDialog = null;     // set null to prevent memory leakage
                startActivity(intent);
            });
            winDateDialog = builder.create();

        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); }

}