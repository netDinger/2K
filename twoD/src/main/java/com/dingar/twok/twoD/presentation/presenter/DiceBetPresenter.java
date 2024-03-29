package com.dingar.twok.twoD.presentation.presenter;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.core.firebase.Get_FirebaseCurrentTime;
import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.twoD.data.model.LotteryModel;
import com.dingar.twok.twoD.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.twoD.domain.interactor.CheckBetableUseCase;
import com.dingar.twok.twoD.domain.interactor.CountDownUseCase;
import com.dingar.twok.twoD.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.twoD.presentation.contract.DiceBetContract;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * {@link com.dingar.twok.twoD.presentation.view.Activity_DiceBet}
 * @see DiceBetContract.Presenter
 */
public class DiceBetPresenter implements DiceBetContract.Presenter {
    private final String TAG = "DiceBetPresenterTWOD";
    private DiceBetContract.View view;

    private CountDownTimer timer;

    /** list of available lotteries*/
    ArrayList<LotteryModel> lotteriesList = new ArrayList<>();

    /** list for adding excluded lotteries (user can't bet these lotteries)*/
    ArrayList<Integer> excludedLotteriesList = new ArrayList<>();

    private final ArrayList<String> winDates;

    LoadBetsUseCase loadBetsUseCase;    //to get the available lottery numbers
    CountDownUseCase countDownUseCase;  //to get the next bet opening time
    BetableTimeUseCase betableTimeUseCase; // to get the bet opening times
    CheckBetableUseCase checkBetableUseCase; // to check if the user can still bet or not

    public DiceBetPresenter(LoadBetsUseCase loadBetsUseCase,
                            CountDownUseCase countDownUseCase,
                            BetableTimeUseCase betableTimeUseCase,CheckBetableUseCase checkBetableUseCase){
        this.loadBetsUseCase = loadBetsUseCase;
        this.checkBetableUseCase = checkBetableUseCase;
        this.countDownUseCase = countDownUseCase;
        this.betableTimeUseCase = betableTimeUseCase;
        winDates = new ArrayList<>();
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

    @Override public void loadLotteries(String prefix, String suffix) {
        int prefx = 0,suffx=0;
        try{
            if (prefix != null&& !prefix.isEmpty()) // null prefix means there is no user selected prefix
                prefx = Integer.parseInt(prefix);
            if (suffix != null && !suffix.isEmpty()) //null suffix means there is no user selected suffix
                suffx = Integer.parseInt(suffix);

            if (prefx >0 && prefx<=6 &&suffx > 0 && suffx<=6 ){//this means user selected both prefix and suffix to get a single bet slip
                lotteriesList.clear();//clear all the previous betSlip
                lotteriesList.add(new LotteryModel(prefix+suffix,false));
                view.onLotteriesLoad(lotteriesList);
                return;
            }

            if (prefx >0 && prefx<=6){ //this means user selected prefix
                lotteriesList.clear();//clear all the previous betSlip
                for (int suf=1; suf<=6; suf++){ //load suffix and combine with selected prefix
                    lotteriesList.add(new LotteryModel(prefix+suf,false));
                }
                view.onLotteriesLoad(lotteriesList);

            }
            else if(suffx > 0 && suffx<=6){ //this means user selected suffix
                lotteriesList.clear();//clear all the previous betSlip
                for (int pref=1; pref<=6;pref++) //load suffix and combine with selected suffix
                    lotteriesList.add(new LotteryModel(suffix+pref,false));
                view.onLotteriesLoad(lotteriesList);
            }

        }catch (Exception exception){
            view.showDialog("ERROR",exception.getMessage(),true);
        }
    }

    @Override
    public boolean isStringValid(String amount) {
        return !amount.isEmpty() && Integer.parseInt(amount)>=100;
    }

    @Override
    public void loadTimeRemaining() {
        countDownUseCase.execute().subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {}

            @Override
            public void onSuccess(@NonNull String s) {
                calculateTimeRemaining(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {}
        });
    }

    @Override
    public void loadBetableTime() {
        betableTimeUseCase.execute().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {}

            @Override
            public void onNext(@NonNull String s) {
                try {
                    winDates.add(DateUtil.timeStampToDate(s));
                }catch (Exception e){
                    Log.e(TAG,"chang e"+e.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Error",e.getMessage());
            }

            @Override
            public void onComplete() {
                view.onBetAbleTimeLoaded(winDates);
            }
        });
    }

    @Override
    public void checkBetable(String date) {
        try {
            checkBetableUseCase.checkBetable(String.valueOf(DateUtil.dateToTimestamp(date)))
                    .subscribe(new SingleObserver<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(Result result) {
                            if (result.isSuccess())
                                view.bet();
                            else
                                view.showDialog("Oops...","ထိုးချိန်ကျော်သွားပါပြီ",true);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showDialog("Oops...",e.getMessage(),true);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**invoked after {@link #loadLotteries()} get all excluded lotteries
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
        if (timer != null)
            timer.cancel(); // remove timer
        view = null;
    }

    //calculate the time remaining to open the next lottery
    private void calculateTimeRemaining(String futureTimeStamp){
        try {
            Get_FirebaseCurrentTime.getInstance().gerCurrentTime().subscribe(new SingleObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {}

                @Override
                public void onSuccess(@NonNull String s) {
                    //function call to create the count down timer
                    timerTask(Long.parseLong(futureTimeStamp),Long.parseLong(s));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
        } catch (Exception e) {
           view.setTimeRemaining("Error Getting Time");
        }
    }

    /**
     *
     * @param future timestamp when next lottery will be opend
     * @param current current server timestamp
     */
    private void timerTask(long future,long current){
        try{
        Long diff = future - current;

        timer = new CountDownTimer(diff,1000){
            @Override
            public void onTick(long remainingTime) {
                @SuppressLint("DefaultLocale")
                String remainTime = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(remainingTime)%60,
                        TimeUnit.MILLISECONDS.toMinutes(remainingTime)%60,
                        TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60);

                view.setTimeRemaining("ထီဖွင့်ရန်: "+remainTime);
            }

            @Override
            public void onFinish() {}
        };
        timer.start();
        }catch (Exception exception){
            Log.e(TAG,exception.getMessage());
        }
    }
}
