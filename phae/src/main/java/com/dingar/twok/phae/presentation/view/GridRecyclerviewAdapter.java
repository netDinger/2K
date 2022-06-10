package com.dingar.twok.phae.presentation.view;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.phae.R;
import com.dingar.twok.phae.data.model.LotteryModel;
import com.dingar.twok.phae.presentation.contract.DiceBetContract;

import java.util.ArrayList;

public class GridRecyclerviewAdapter extends RecyclerView.Adapter<GridRecyclerviewAdapter.ViewHolder> {

    private final String TAG = "GridRecyclerviewAdapter";

    //for interacting with the activity via contract
    private final DiceBetContract.View view;

    //list of available lotteries
    ArrayList<LotteryModel> lotteryList = new ArrayList<>();

    public GridRecyclerviewAdapter(DiceBetContract.View view){
        this.view = view;
    }

    public void setData(ArrayList<LotteryModel> lotteryList){
        this.lotteryList = lotteryList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LotteryModel lottery = lotteryList.get(position);
        holder.lottery_number.setText(lottery.getLotteryNumber());

        //set the background color according to it is selected or not
        holder.lottery_number.setBackgroundColor(lottery.isSelected()?Color.parseColor("#DDAB12"):  //golden color
                Color.parseColor("#188830")); //green color

        //on a lottery is selected
        holder.lottery_number.setOnClickListener(view ->{
            if (lottery.isSelected())//click on already selected item
                this.view.removeBetSlip(lottery); //remove lottery
            else
                this.view.addBetSlip(lottery);

            lottery.setSelected(!lottery.isSelected());
            notifyItemChanged(position);

            Log.e(TAG,lottery.getLotteryNumber());
        });
    }

    @Override
    public int getItemCount() {
        return lotteryList.size();
    }

    //view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lottery_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottery_number = itemView.findViewById(R.id.lottery_number);
        }
    }
}
