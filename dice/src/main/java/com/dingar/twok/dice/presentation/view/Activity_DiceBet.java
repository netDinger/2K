package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.dice.R;
import com.dingar.twok.dice.di.component.ComponentProviderDice;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.core.ui.GridRecyclerView;
import com.dingar.twok.dice.presentation.contract.DiceBetContract;

import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.Inflater;

import javax.inject.Inject;

/**
 * show the available (bet able) lottery list to user
 * @see DiceBetContract for better understanding
 */
public class Activity_DiceBet extends AppCompatActivity implements DiceBetContract.View {
    private static final String TAG = "Activity_DiceBet";

    @Inject
    public DiceBetContract.Presenter presenter;

    DiceBetComponent diceBetComponent;
    long winDate; //holder for user selected win date

    private GridRecyclerviewAdapter gridRecyclerviewAdapter;
    private TextView time_remaining,quick_choose;
    private EditText amount;

    private AlertDialog alertDialog; //to show the available win date

    private ArrayList<LotteryModel> lotteryModels;  //contains user selected lotteries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        //initiate the login component and inject the view
        diceBetComponent = ((ComponentProviderDice)getApplicationContext())
                .provideDiceBetComponent();
        diceBetComponent.inject(this);
        presenter.setView(this);

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

    @Override
    public void showDialog(String title, String message, boolean cancelable) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(title).setMessage(message).setCancelable(true);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void bet() {
        Intent intent = new Intent();
        intent.putExtra("betSlips",lotteryModels);
        intent.putExtra("amount",amount.getText().toString().trim());
        intent.putExtra("winDate", winDate);
        intent.setClass(this,Activity_Bet_Slips.class);
        alertDialog = null;     // set null to prevent memory leakage
        startActivity(intent);
    }

    private void initiate(){
        lotteryModels = new ArrayList<>();
        //load available bet slips (not all because some are excluded by provider)
        presenter.loadLotteries();
        presenter.loadBetableTime();
        presenter.loadTimeRemaining();
    }

    private void widgets(){
        addToolbar();

        //recycler grid layout initiation
        GridRecyclerView gridRecyclerView = findViewById(R.id.betSlips);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerviewAdapter = new GridRecyclerviewAdapter(this);
        gridRecyclerView.setAdapter(gridRecyclerviewAdapter);

        time_remaining = findViewById(R.id.time_remaining);
        Button bet = findViewById(R.id.bet);
        ImageView history = findViewById(R.id.history);
        amount = findViewById(R.id.amount);
        quick_choose = findViewById(R.id.quick_choose);
        quick_choose.setOnClickListener(view-> showQuickChooseOptionDialog());

        ImageView help = findViewById(R.id.help);
        bet.setOnClickListener(v-> {    //bet the user selected bet slip
            if (presenter.isStringValid(amount.getText().toString())){ //if amount is not empty
                if (!lotteryModels.isEmpty()) {  // if at least one lottery is selected
                    if (alertDialog != null) {
                        alertDialog.show();
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
    }//widgets

    /**
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
                alertDialog.dismiss();
                presenter.checkBetable(dates.get(picker.getValue()));
                try {
                    winDate = DateUtil.dateToTimestamp(dates.get(picker.getValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void showQuickChooseOptionDialog(){
        View view = View.inflate(this,R.layout.item_quick_choose,null);
        EditText prefix,suffix;
        prefix = view.findViewById(R.id.prefix);
        suffix = view.findViewById(R.id.suffix);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setTitle(R.string.quick_choose)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    Toast.makeText(this, "prefix"+prefix.getText().toString(), Toast.LENGTH_SHORT).show();
                    presenter.loadLotteries(prefix.getText().toString(),suffix.getText().toString());
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
                });
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}