package com.dingar.twok.dice.presentation.view;

import android.annotation.SuppressLint;
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

    private final String TAG = "BetListRecyclerAdapter";

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
    public void addData(ArrayList<LotteryModel> lotteryModels){
        this.lotteryModels = new ArrayList<>();
       this.lotteryModels = lotteryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bet_slip, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LotteryModel lotteryModel = lotteryModels.get(position);
        holder.lottery.setText(lotteryModel.getLotteryNumber());
        holder.amount.setText(String.valueOf(lotteryModel.getAmount()));

        holder.delete.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            Log.e("position",""+position);
            Log.e("adapter position",""+ pos);
           try {
                //remove from recyclerview
                lotteryModels.remove(holder.getAdapterPosition());
                notifyItemRemoved(pos);
               // notifyItemRangeChanged(position, lotteryModels.size()-1);

                //presenter will removed it from bet list and sub the total amount too
                presenter.onBetRemoved(pos);
            }catch (Exception e){
                Log.e("Error",e.getMessage());
            }
        });

        holder.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty())
                    onTextChange(lotteryModel, holder.getAdapterPosition(), Integer.parseInt(editable.toString()));
                else
                    onTextChange(lotteryModel,holder.getAdapterPosition(),0);
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

    private void onTextChange(LotteryModel lotteryModel,int position,int amount){
        try {
            //change amount for recycler view
            lotteryModels.get(position).setAmount(amount);
            //presenter will changed the amount of the lottery in bet list
            presenter.onAmountChanged(position, String.valueOf(amount));
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

}

