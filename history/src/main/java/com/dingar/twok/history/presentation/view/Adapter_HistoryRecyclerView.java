package com.dingar.twok.history.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;

import java.util.ArrayList;

public class Adapter_HistoryRecyclerView extends RecyclerView.Adapter<Adapter_HistoryRecyclerView.ViewHolder> {

    ArrayList<BetSlipModel> betSlipModelArrayList;

    public Adapter_HistoryRecyclerView(ArrayList<BetSlipModel> betSlipModelArrayList){
        this.betSlipModelArrayList = new ArrayList<>();
        this.betSlipModelArrayList = betSlipModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return betSlipModelArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
