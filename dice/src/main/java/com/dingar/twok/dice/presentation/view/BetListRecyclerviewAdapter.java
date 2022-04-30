package com.dingar.twok.dice.presentation.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.contract.BetListContract;

import java.util.ArrayList;

import javax.inject.Inject;

public class BetListRecyclerviewAdapter extends RecyclerView.Adapter<BetListRecyclerviewAdapter.ViewHolder> {

    private final String TAG = "BetListRecyclerviewAdapter";
    @Inject
    public BetListContract.Presenter presenter;

    //list of selected lotteries (from previous activity) to show
    ArrayList<LotteryModel> lotteryModels;

    public BetListRecyclerviewAdapter(ArrayList<LotteryModel> lotteryModels){
        this.lotteryModels = new ArrayList<>();
        this.lotteryModels = lotteryModels;
    }

    /**
     * @param lotteryModels list of lotteries loaded by View (with amount for each lottery)
     */
    public void setData(ArrayList<LotteryModel> lotteryModels){
        this.lotteryModels = new ArrayList<>();
        this.lotteryModels = lotteryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_slip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LotteryModel lotteryModel = lotteryModels.get(position);
        holder.lottery.setText(lotteryModel.getLotteryNumber());
        holder.amount.setText(String.valueOf(lotteryModel.getAmount()));

        holder.delete.setOnClickListener(view -> {
            //presenter will removed it from bet list
            presenter.onBetRemoved(position);

            //remove from recyclerview
            lotteryModels.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,lotteryModels.size());
            Log.e("lotteryModels.size",""+lotteryModels.size());
        });

        holder.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()){
                    try {
                        //change amount for recycler view
                        lotteryModel.setAmount(Integer.parseInt(editable.toString()));
                        lotteryModels.remove(holder.getAdapterPosition());
                        lotteryModels.add(holder.getAdapterPosition(), lotteryModel);

                        //presenter will changed the amount of the lottery in bet list
                        presenter.onAmountChanged(holder.getAdapterPosition(), editable.toString());
                    }catch (Exception e){
                        Log.e(TAG,e.getMessage());
                    }
                }
            }
        });//end of addTextChangedListener
    }

    @Override
    public int getItemCount() {
        return lotteryModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lottery;
        EditText amount;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottery = itemView.findViewById(R.id.lottery_number);
            amount = itemView.findViewById(R.id.amount);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}

