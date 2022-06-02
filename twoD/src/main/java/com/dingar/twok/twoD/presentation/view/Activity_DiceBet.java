package com.dingar.twok.twoD.presentation.view;

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
import com.dingar.twok.twoD.R;
import com.dingar.twok.twoD.data.model.LotteryModel;
import com.dingar.twok.twoD.di.component.ComponentProviderTwoD;
import com.dingar.twok.twoD.di.component.TwoDBetComponent;
import com.dingar.twok.twoD.presentation.contract.DiceBetContract;

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

    TwoDBetComponent twoDBetComponent;

    private GridRecyclerviewAdapter gridRecyclerviewAdapter;
    private TextView time_remaining,win_date;
    private ImageView help;

    private AlertDialog alertDialog; //to show the available win date

    private ArrayList<LotteryModel> lotteryModels;  //contains user selected lotteries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twod_bet);
        time_remaining = findViewById(R.id.time_remaining);

        //initiate the login component and inject the view
        twoDBetComponent = ((ComponentProviderTwoD)getApplicationContext())
                .provideTwoDBetComponent();
        twoDBetComponent.inject(this);
        presenter.setView(this);

        //recycler grid layout initiation
        GridRecyclerView gridRecyclerView = findViewById(R.id.betSlips);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerviewAdapter = new GridRecyclerviewAdapter(this);
        gridRecyclerView.setAdapter(gridRecyclerviewAdapter);

        initiate();
        widgets();
    }

    @Override
    public void onBackPressed() {
        presenter.dropView();
        alertDialog = null;
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

    private void initiate(){
        //load available bet slips (not all because some are excluded by provider)
        presenter.loadLotteries();
        presenter.loadBetableTime();
        presenter.loadTimeRemaining();
    }

    private void widgets(){
        addToolbar();
        lotteryModels = new ArrayList<>();
        Button bet = findViewById(R.id.bet);
        Button history = findViewById(R.id.history);
        EditText amount = findViewById(R.id.amount);
        win_date = findViewById(R.id.win_date);
        help = findViewById(R.id.help);
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

        //navigate to lucky number history
        history.setOnClickListener(view -> startActivity(new Intent(this,Activity_Win_Lotteries.class)));
        win_date.setOnClickListener(view ->{
            if (alertDialog == null){
                //if winDates are still retrieving
                Toast.makeText(this,R.string.wait,Toast.LENGTH_SHORT).show();
            }
            else
                alertDialog.show();
        });

        help.setOnClickListener(view -> startActivity(new Intent(this,Activity_Help.class)));
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
            picker.setDisplayedValues((String[]) dates.toArray());

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.select_win_date);
            builder.setView(picker);
            builder.setCancelable(true);
            builder.setPositiveButton("Select", (dialogInterface, i) -> {
                win_date.setText(dates.get(picker.getValue()));
                alertDialog.dismiss();
            });
            alertDialog = builder.create();

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