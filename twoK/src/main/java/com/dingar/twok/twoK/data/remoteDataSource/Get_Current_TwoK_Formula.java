package com.dingar.twok.twoK.data.remoteDataSource;

import io.reactivex.Single;

/**
 * Get the current Thai two D result in realtime form official website
 */
public class Get_Current_TwoK_Formula {
    private final String TAG = "Get_Current_TwoD";

    //Singleton
    private Get_Current_TwoK_Formula(){}
    private static Get_Current_TwoK_Formula instance;
    public static Get_Current_TwoK_Formula getInstance(){
        if (instance == null)
            instance = new Get_Current_TwoK_Formula();
        return instance;
    }

    /**
     * @return Single observer for combined 2K result
     */
    public Single<String> getResult(){
        return Single.create(emitter -> {

        });
    }

}
