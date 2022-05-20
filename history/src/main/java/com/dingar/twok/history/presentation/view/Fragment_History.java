package com.dingar.twok.history.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.di.component.ComponentProviderHistory;
import com.dingar.twok.history.di.component.HistoryComponent;
import com.dingar.twok.history.presentation.contract.HistoryContract;

import javax.inject.Inject;

/**
 * show the history of user betSlips
 */
public class Fragment_History extends Fragment implements HistoryContract.View {

    @Inject
    HistoryContract.Presenter presenter;

    HistoryComponent historyComponent;
    public Fragment_History() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initiate component and inject the view
        historyComponent = ((ComponentProviderHistory)requireActivity()
                .getApplicationContext()).provideHistoryComponent();
        historyComponent.inject(this); //inject the view

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        widgets(view);
        initiate();

        return view;
    }

    @Override
    public void onHistoryLoaded(BetSlipModel betSlipModel) {

    }

    private void widgets(View view){
        presenter.setView(this);
    }

    private void initiate(){
        presenter.loadHistory();
    }
}