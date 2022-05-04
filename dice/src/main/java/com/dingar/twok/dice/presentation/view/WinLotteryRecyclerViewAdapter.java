package com.dingar.twok.dice.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;

import java.util.ArrayList;

public class WinLotteryRecyclerViewAdapter extends RecyclerView.Adapter<WinLotteryRecyclerViewAdapter.ViewHolder> {

    ArrayList<WinLotteryModel> winLotteryArrayList;

    public WinLotteryRecyclerViewAdapter(ArrayList<WinLotteryModel> winLotteryArrayList){
        winLotteryArrayList = new ArrayList<>();
        this.winLotteryArrayList = winLotteryArrayList;
    }

    public void setData(ArrayList<WinLotteryModel> winLotteryArrayList){
        this.winLotteryArrayList = winLotteryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lucky_lottery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,lucky_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            lucky_number = itemView.findViewById(R.id.lottery_number);
        }
    }
}

