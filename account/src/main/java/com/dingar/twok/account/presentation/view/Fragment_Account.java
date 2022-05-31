package com.dingar.twok.account.presentation.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingar.twok.account.R;
import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.account.di.component.ComponentProviderAccount;
import com.dingar.twok.account.presentation.contract.AccountContract;

import javax.inject.Inject;

public class Fragment_Account extends Fragment implements AccountContract.View {

    @Inject
    AccountContract.Presenter presenter; //inject the presenter

    AccountComponent accountComponent;  //component
    private TextView username,id,balance,points;

    public Fragment_Account(){} //empty constructor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initiate the component and inject the view
        accountComponent = ((ComponentProviderAccount) requireActivity().getApplicationContext()).provideAccountComponent();
        accountComponent.inject(this);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        widgets(view);
        initiate();
        return view;
    }

    private void widgets(View view){
        username = view.findViewById(R.id.username);
        id = view.findViewById(R.id.id);
        RelativeLayout layout = view.findViewById(R.id.balance_layout);
        balance = layout.findViewById(R.id.balance);
        points = layout.findViewById(R.id.points);
        TextView logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(view1 -> {
            Toast.makeText(requireContext(),"Logout Successfully",Toast.LENGTH_SHORT).show();
            presenter.logout();});

        id.setOnClickListener(v->{ //on user id textview is clicked
            //copy id to clipboard
            ClipboardManager manager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(id.getText().toString(),id.getText().toString());
            manager.setPrimaryClip(clipData);

            Toast.makeText(requireContext(),"Copied to ClipBoard",Toast.LENGTH_SHORT).show();
        });

        balance.setOnClickListener(view1 -> startActivity(new Intent(requireContext(),Activity_Balance.class)));

    }

    private void initiate() {
        presenter.setView(this);        //attach the view
        presenter.loadUserBalance();    //get user balance
        presenter.loadUserInfo();       //get user info
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
        points.setText("1000 P");
    }

    @Override
    public void onUserInfoLoaded(User user) {
       username.setText(user.getName());
       id.setText(user.getUid());
    }
}