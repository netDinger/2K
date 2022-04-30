package com.dingar.twok.dice.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.dice.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.dice.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.presentation.contract.DiceBetContract;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class DiceBetPresenter implements DiceBetContract.Presenter {
    private final String TAG = "DiceBetPresenter";
    private DiceBetContract.View view;

    /** list of available lotteries*/
    ArrayList<LotteryModel> lotteriesList = new ArrayList<>();

    /** list for adding excluded lotteries*/
    ArrayList<Integer> excludedLotteriesList = new ArrayList<>();

    LoadBetsUseCase loadBetsUseCase;
    GetBalanceUseCase getBalanceUseCase;

    public DiceBetPresenter(LoadBetsUseCase loadBetsUseCase,GetBalanceUseCase getBalanceUseCase){
        this.loadBetsUseCase = loadBetsUseCase;
        this.getBalanceUseCase = getBalanceUseCase;
    }

    //retrieve available lotteries
    @Override
    public void loadLotteries() {
        //observe on excluded lotteries
        //i.e lotteries blocked by admin
        loadBetsUseCase.execute().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }
            @Override
            public void onNext(@NonNull String s) {
                try {
                    excludedLotteriesList.add(Integer.parseInt(s));
                }catch (Exception exception){
                    Log.e(TAG,exception.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                initiateLotteryList();
            }
        });
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
    public boolean isStringValid(String amount) {
        return !amount.isEmpty() && Integer.parseInt(amount)>=100;
    }

    /**
     * for 2d lotteries list 00 to 99, excluding numbers blocked by admin*/
    private void initiateLotteryList(){
        String s;
        for (int i=0; i<=99;i++){
            if (excludedLotteriesList.contains(i))
                //if number is in excluded list
                continue;

            if (i<10)//add prefix if number less than 10 (to get the formation of 00,01,...)
                s = "0"+i;
            else
                s = String.valueOf(i);
            lotteriesList.add(new LotteryModel(s,false));
        }

        //tell the view to load the lotteries
        view.onLotteriesLoad(lotteriesList);
    }

    @Override
    public void setView(DiceBetContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
