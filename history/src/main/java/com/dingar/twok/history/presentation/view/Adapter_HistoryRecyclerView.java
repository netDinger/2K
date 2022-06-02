package com.dingar.twok.history.presentation.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;

import java.util.ArrayList;

public class Adapter_HistoryRecyclerView extends RecyclerView.Adapter<Adapter_HistoryRecyclerView.ViewHolder> {

    private final String TAG = "HistoryRecyclerView";

    ArrayList<BetSlipModel> betSlipModelArrayList;

    public Adapter_HistoryRecyclerView(){
        this.betSlipModelArrayList = new ArrayList<>();
    }

    protected void setData(BetSlipModel betSlipModel){
        this.betSlipModelArrayList.add(betSlipModel);
        notifyItemInserted(betSlipModelArrayList.size()-1);
    }

    //Clear all the data to load the new ones
    @SuppressLint("NotifyDataSetChanged")
    protected void refreshData(){
        this.betSlipModelArrayList.clear();
        notifyDataSetChanged();
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

        holder.moreLayout.setVisibility(betSlipModel.isVisible()?View.VISIBLE:View.GONE);

        holder.seeMore.setOnClickListener(view -> {
            Log.e("position",""+ position+"  "+ holder.getAdapterPosition());
            betSlipModelArrayList.get(position).setVisible(!betSlipModel.isVisible());
            notifyItemChanged(position);
            Log.e(TAG,betSlipModel.getLuckyNumber());
        });

    }

    @Override
    public int getItemCount() {
        return betSlipModelArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lottery_number,betDate,betSlipId,amount;

        public RelativeLayout moreLayout,seeMore;   // the invisible layout

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottery_number = itemView.findViewById(R.id.lottery_number);
            betDate = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            betSlipId = itemView.findViewById(R.id.betSlipId);
            seeMore = itemView.findViewById(R.id.historyLayout);
            moreLayout = itemView.findViewById(R.id.moreLayout);
        }
    }
}
