package com.dingar.twok.history.presentation.view;

import android.os.Bundle;

import android.widget.Toast;
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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import javax.inject.Inject;

/**
 * show the history of user betSlips
 */
public class Fragment_History extends Fragment implements HistoryContract.View {

    @Inject
    public HistoryContract.Presenter presenter;

    private Adapter_HistoryRecyclerView recyclerViewAdapter;
    private RecyclerView betHistoryRecyclerView;
    private Chip twoD,threeD,twoK,phae,dice;
    private ChipGroup chipGroup;

    public Fragment_History() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initiate component and inject the view
        HistoryComponent historyComponent = ((ComponentProviderHistory) requireActivity()
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

        twoK = view.findViewById(R.id.load2K);
        twoD = view.findViewById(R.id.load2D);
        dice = view.findViewById(R.id.loadDice);
        phae = view.findViewById(R.id.loadPhae);
        threeD = view.findViewById(R.id.load3D);

        twoD.setOnClickListener(v->{
            recyclerViewAdapter.refreshData();  //remove data on new data was fetched
            presenter.load2DHistory();
        });

        threeD.setOnClickListener(v->{
            recyclerViewAdapter.refreshData();
            presenter.load3DHistory();
        });

        dice.setOnClickListener(v->{
            recyclerViewAdapter.refreshData();
            presenter.loadDiceHistory();
        });

        twoK.setOnClickListener(v->{
            recyclerViewAdapter.refreshData();
            presenter.load2KHistory();
        });

        phae.setOnClickListener(v->{
            recyclerViewAdapter.refreshData();
            presenter.loadPhaeHistory();
        });
    }

    private void initiate(){
        recyclerViewAdapter = new Adapter_HistoryRecyclerView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        betHistoryRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL);
        betHistoryRecyclerView.addItemDecoration(dividerItemDecoration);
        betHistoryRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setContext(requireContext());
        presenter.load2DHistory();
    }

    @Override public void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}