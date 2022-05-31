package com.dingar.twok.history.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.di.component.ComponentProviderHistory;
import com.dingar.twok.history.di.component.HistoryComponent;
import com.dingar.twok.history.presentation.contract.HistoryContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * show the history of user betSlips
 */
public class Fragment_History extends Fragment implements HistoryContract.View {

    @Inject
    public HistoryContract.Presenter presenter;

    private HistoryComponent historyComponent;
    private Adapter_HistoryRecyclerView recyclerViewAdapter;
    private ArrayList<BetSlipModel> betSlipModelArrayList;
    private RecyclerView betHistoryRecyclerView;

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
        recyclerViewAdapter.setData(betSlipModel);
    }

    private void widgets(View view){
        betHistoryRecyclerView = view.findViewById(R.id.betHistory);
        presenter.setView(this);
    }

    private void initiate(){
        betSlipModelArrayList = new ArrayList<>();
        recyclerViewAdapter = new Adapter_HistoryRecyclerView(betSlipModelArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        betHistoryRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL);
        betHistoryRecyclerView.addItemDecoration(dividerItemDecoration);
        betHistoryRecyclerView.setAdapter(recyclerViewAdapter);
        presenter.loadHistory();
    }
}