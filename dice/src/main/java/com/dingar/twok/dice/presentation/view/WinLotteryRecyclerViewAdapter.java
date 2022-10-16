package com.dingar.twok.dice.presentation.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;

import java.util.ArrayList;

public class WinLotteryRecyclerViewAdapter extends RecyclerView.Adapter<WinLotteryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<WinLotteryModel> winLotteryArrayList;

    public WinLotteryRecyclerViewAdapter(){
        this.winLotteryArrayList = new ArrayList<>();

    }

    public void setData(ArrayList<WinLotteryModel> winLotteryArrayList){
        this.winLotteryArrayList = winLotteryArrayList;
    }

    public void addData(WinLotteryModel winLotteryModel){
        this.winLotteryArrayList.add(winLotteryModel);
        notifyItemInserted(winLotteryArrayList.size()-1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lucky_lottery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WinLotteryModel winLotteryModel = winLotteryArrayList.get(position);
        try {
            holder.date.setText(DateUtil.timeStampToDate(winLotteryModel.getDate()));
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
        holder.lucky_number.setText(winLotteryModel.getLucky_number());
    }

    @Override
    public int getItemCount() {
        return winLotteryArrayList.size();
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
