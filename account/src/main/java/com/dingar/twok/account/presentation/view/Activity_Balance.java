package com.dingar.twok.account.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.dingar.twok.account.R;
import com.dingar.twok.account.di.component.BalanceComponent;
import com.dingar.twok.account.di.component.ComponentProviderAccount;
import com.dingar.twok.account.presentation.contract.BalanceContract;

import java.util.Objects;
import javax.inject.Inject;

/**
 * to show user balance ,deposit and withdraw
 */
public class Activity_Balance extends AppCompatActivity implements BalanceContract.View {

    @Inject
    public BalanceContract.Presenter presenter;

    private BalanceComponent balanceComponent;

    private Button withdraw,deposit;
    private TextView helpBalance,balance,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        addToolbar();

        //initiate component
        balanceComponent = ((ComponentProviderAccount)getApplicationContext()).provideBalanceComponent();
        balanceComponent.inject(this);// inject the activity

        widgets();
        initiate();
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
    }

    @Override
    public void onOTPLoaded(String code) {
        this.code.setText(code);
    }

    private void widgets(){
        withdraw = findViewById(R.id.withdraw);
        deposit = findViewById(R.id.deposit);
        helpBalance = findViewById(R.id.helpBalance);
        code = findViewById(R.id.code);
        balance = findViewById(R.id.balance);

        deposit.setOnClickListener(view -> toTelegram());
        withdraw.setOnClickListener(view -> toTelegram());

        code.setOnClickListener(view -> {
            ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData data = ClipData.newPlainText(code.getText().toString(),code.getText().toString());
            manager.setPrimaryClip(data);
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        helpBalance.setOnClickListener(view->startActivity(new Intent(this,Activity_Balance_Help.class)));
    }

    private void initiate(){
        presenter.setView(this);
        presenter.loadBalance();
        presenter.loadOTP();
    }

    private void toTelegram(){
        String package_name ="org.telegram.messenger";
        String phone = "09458871680";
        try {
            Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("viber://pa?chatURI=publicaccounturi"));
            shareIntent.setPackage(package_name); //viber package name
            startActivity(Intent.createChooser(shareIntent, "Test Title"));
        }catch (ActivityNotFoundException exception){
            Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); }

}