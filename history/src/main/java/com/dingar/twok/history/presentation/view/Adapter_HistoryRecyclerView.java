package com.dingar.twok.history.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;

import java.util.ArrayList;

public class Adapter_HistoryRecyclerView extends RecyclerView.Adapter<Adapter_HistoryRecyclerView.ViewHolder> {

    ArrayList<BetSlipModel> betSlipModelArrayList;

    public Adapter_HistoryRecyclerView(ArrayList<BetSlipModel> betSlipModelArrayList){
        this.betSlipModelArrayList = new ArrayList<>();
        this.betSlipModelArrayList = betSlipModelArrayList;
    }

    protected void setData(BetSlipModel betSlipModel){
        this.betSlipModelArrayList.add(betSlipModel);
        notifyItemInserted(betSlipModelArrayList.size()-1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BetSlipModel betSlipModel = betSlipModelArrayList.get(position);
        holder.lottery_number.setText(betSlipModel.getLuckyNumber());
        holder.betDate.setText(DateUtil.timeStampToDate(String.valueOf(betSlipModel.getDate())));
        holder.amount.setText(betSlipModel.getAmount());
        holder.betSlipId.setText(betSlipModel.getBetSlipId());
    }

    @Override
    public int getItemCount() {
        return betSlipModelArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lottery_number,betDate,betSlipId,amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottery_number = itemView.findViewById(R.id.lottery_number);
            betDate = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            betSlipId = itemView.findViewById(R.id.betSlipId);
        }
    }
}
