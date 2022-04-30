package com.dingar.twok.dice.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.interactor.BetUseCase;
import com.dingar.twok.dice.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.contract.BetListContract;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BetSlipPresenter implements BetListContract.Presenter {

    private BetListContract.View view;
    BetUseCase betUseCase;
    GetBalanceUseCase getBalanceUseCase;
    private ArrayList<LotteryModel> lotteryModelArrayList;
    private int totalAmount;

    public BetSlipPresenter(BetUseCase betUseCase,GetBalanceUseCase getBalanceUseCase){
        this.betUseCase = betUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
        lotteryModelArrayList = new ArrayList<>();
    }

    @Override
    public void onBetClick() {
        if(lotteryModelArrayList != null)
        betUseCase.execute(lotteryModelArrayList).subscribe(new SingleObserver<Result>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onSuccess(@NonNull Result result) {
                if(result.isSuccess())
                view.onBettingSuccess();

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        else
            Log.e("BetSlipPresenter","list is null");
    }

    @Override
    public void onLoadBalance() {
        getBalanceUseCase.execute().subscribe(new SingleObserver<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@NonNull Double aDouble) {
                view.onBalanceLoaded(aDouble+"Kyats");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onBalanceLoaded("0Kyats");
            }
        });
    }

    @Override
    public void setBetList(ArrayList<LotteryModel> lotteryModels) {
        lotteryModelArrayList = lotteryModels;
    }

    @Override
    public void calculateTotalAmount() {
        totalAmount = lotteryModelArrayList.get(0).getAmount()
                *lotteryModelArrayList.size();
        view.onTotalAmountCalculated(totalAmount);
    }

    @Override
    public void onBetRemoved(int position) {
        lotteryModelArrayList.remove(position);
        totalAmount = 0;
        for (LotteryModel model: lotteryModelArrayList){
            totalAmount+= model.getAmount();
        }
        view.onTotalAmountCalculated(totalAmount);
    }

    @Override
    public void onAmountChanged(int position, String amount) {
        lotteryModelArrayList.get(position).setAmount(Integer.parseInt(amount));
        totalAmount = 0;
        for (LotteryModel model: lotteryModelArrayList){
            totalAmount += model.getAmount();
        }
        view.onTotalAmountCalculated(totalAmount);
    }

    @Override
    public void setView(BetListContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
