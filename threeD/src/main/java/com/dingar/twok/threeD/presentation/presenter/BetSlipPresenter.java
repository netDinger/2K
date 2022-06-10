package com.dingar.twok.threeD.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.threeD.data.model.LotteryModel;
import com.dingar.twok.threeD.domain.interactor.BetUseCase;
import com.dingar.twok.threeD.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.threeD.presentation.contract.BetListContract;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BetSlipPresenter implements BetListContract.Presenter {

    private BetListContract.View view;
    BetUseCase betUseCase;
    GetBalanceUseCase getBalanceUseCase;
    private ArrayList<LotteryModel> lotteryModelArrayList;
    private double totalAmount;
    private double balance;


    public BetSlipPresenter(BetUseCase betUseCase,GetBalanceUseCase getBalanceUseCase){
        this.betUseCase = betUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
        lotteryModelArrayList = new ArrayList<>();
    }

    @Override
    public void onBetClick() {
        if(lotteryModelArrayList != null) {
            if (balance >= totalAmount) {
            betUseCase.execute(lotteryModelArrayList,balance).subscribe(new SingleObserver<Result>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onSuccess(@NonNull Result result) {
                    if (result.isSuccess())
                        view.onBettingSuccess();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }
            });
        } else
            view.showToast("No enough balance!!!");
        } else
            view.showToast("No Lottery to bet!!!");
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
                balance = aDouble;
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
    public void onBetRemoved() {
        totalAmount = 0;
        //re calculate the total amount
        for (LotteryModel model: lotteryModelArrayList) {
            totalAmount += model.getAmount();
        }
        view.onTotalAmountCalculated(totalAmount);
    }

    @Override
    public void onAmountChanged(int position, String amount) {
        Log.e("change size",lotteryModelArrayList.size()+"");
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
