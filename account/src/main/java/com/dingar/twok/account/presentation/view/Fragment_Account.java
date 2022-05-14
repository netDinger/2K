package com.dingar.twok.account.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingar.twok.account.R;
import com.dingar.twok.account.presentation.contract.AccountContract;

import javax.inject.Inject;

public class Fragment_Account extends Fragment implements AccountContract.View {

    @Inject
    AccountContract.Presenter presenter;

    public Fragment_Account(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        widgets();
        initiate();
        return view;
    }

    private void widgets(){}

    private void initiate() {
        presenter.setView(this);
        presenter.loadUserBalance();
    }

    @Override
    public void onBalanceLoaded(String balance) {

    }
}