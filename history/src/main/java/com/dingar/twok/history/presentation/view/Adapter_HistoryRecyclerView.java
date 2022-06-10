package com.dingar.twok.history.presentation.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.history.R;
import com.dingar.twok.history.data.model.BetSlipModel;

import java.util.ArrayList;

public class Adapter_HistoryRecyclerView extends RecyclerView.Adapter<Adapter_HistoryRecyclerView.ViewHolder> {

    private final String TAG = "HistoryRecyclerView";

    ArrayList<BetSlipModel> betSlipModelArrayList;
    private Context context;

    public Adapter_HistoryRecyclerView(){
        this.betSlipModelArrayList = new ArrayList<>();
    }

    protected void setData(BetSlipModel betSlipModel){
        this.betSlipModelArrayList.add(betSlipModel);
        notifyItemInserted(betSlipModelArrayList.size()-1);
    }

    protected void setContext(Context context){
        this.context = context;
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
        try {
            holder.betDate.setText(DateUtil.timeStampToDate(String.valueOf(betSlipModel.getDate())));
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        holder.amount.setText(betSlipModel.getAmount());
        holder.betSlipId.setText(betSlipModel.getBetSlipId());
        holder.iswin.setText(betSlipModel.isWin()?R.string.win:R.string.lose);

        holder.moreLayout.setVisibility(betSlipModel.isVisible()?View.VISIBLE:View.GONE);

        holder.seeMore.setOnClickListener(view -> {
            Log.e("position",""+ position+"  "+ holder.getAdapterPosition());
            betSlipModelArrayList.get(position).setVisible(!betSlipModel.isVisible());
            notifyItemChanged(position);
            Log.e(TAG,betSlipModel.getLuckyNumber());
        });

        holder.betSlipId.setOnClickListener(v->{
            //copy id to clipboard
            ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(holder.betSlipId.getText().toString(),holder.betSlipId.getText().toString());
            manager.setPrimaryClip(clipData);
            Toast.makeText(context,"Copied to ClipBoard",Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return betSlipModelArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lottery_number,betDate,betSlipId,amount,iswin;

        public RelativeLayout moreLayout,seeMore;   // the invisible layout

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottery_number = itemView.findViewById(R.id.lottery_number);
            betDate = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            betSlipId = itemView.findViewById(R.id.betSlipId);
            seeMore = itemView.findViewById(R.id.historyLayout);
            moreLayout = itemView.findViewById(R.id.moreLayout);
            iswin = itemView.findViewById(R.id.isWin);
        }
    }
}
