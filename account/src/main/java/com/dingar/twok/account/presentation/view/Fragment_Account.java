package com.dingar.twok.account.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingar.twok.account.R;
import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.account.di.component.ComponentProviderAccount;
import com.dingar.twok.account.presentation.contract.AccountContract;

import javax.inject.Inject;

public class Fragment_Account extends Fragment implements AccountContract.View {

    @Inject
    AccountContract.Presenter presenter;

    AccountComponent accountComponent;
    private TextView username,id,balance;

    public Fragment_Account(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        TextView logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(view1 -> presenter.logout());
    }

    private void initiate() {
        presenter.setView(this);
        presenter.loadUserBalance();
        presenter.loadUserInfo();
    }

    @Override
    public void onBalanceLoaded(String balance) {
        this.balance.setText(balance);
    }

    @Override
    public void onUserInfoLoaded(User user) {
       username.setText(user.getUserName());
       id.setText(user.getUid());
    }
}