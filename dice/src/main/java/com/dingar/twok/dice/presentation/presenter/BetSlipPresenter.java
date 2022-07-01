package com.dingar.twok.dice.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.interactor.BetUseCase;
import com.dingar.twok.dice.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.dice.domain.interactor.GetPointUseCase;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.contract.BetListContract;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BetSlipPresenter implements BetListContract.Presenter {

    private BetListContract.View view;
    BetUseCase betUseCase;
    GetBalanceUseCase getBalanceUseCase;
    GetPointUseCase getPointUseCase;
    private ArrayList<LotteryModel> lotteryModelArrayList;
    private double totalAmount;
    private double balance,points;

    public BetSlipPresenter(BetUseCase betUseCase,GetBalanceUseCase getBalanceUseCase,
                            GetPointUseCase getPointUseCase){
        this.betUseCase = betUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
        this.getPointUseCase = getPointUseCase;
        lotteryModelArrayList = new ArrayList<>();
    }

    @Override
    public void onBetWithBalance() {
        if(lotteryModelArrayList != null) {
            if (balance>= totalAmount) {
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
            }else
                view.showToast("No enough balance!!!");
        } else
            view.showToast("No lottery to bet!!!");
    }

    @Override
    public void onBetWithPoint() {
        if(lotteryModelArrayList != null) {
            if (points>= totalAmount){
                betUseCase.betByPoint(lotteryModelArrayList,points).subscribe(new SingleObserver<Result>() {
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

            }else // if not enough balance
                view.showToast("Not enough balance!!!");
        } else  // if lottery list is empty
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
                balance = aDouble;
                view.onBalanceLoaded(String.valueOf(aDouble));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onBalanceLoaded("O");
            }
        });
    }

    @Override
    public void loadPoint() {
        getPointUseCase.getPoint().subscribe(new SingleObserver<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Double point) {
                view.onPointLoaded("Points: "+point);
                points = point;
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onPointLoaded("Points: 0");
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
