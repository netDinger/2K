package com.dingar.twok.twoK.di.component;

import com.dingar.twok.twoK.di.module.BetSlipModule;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.presentation.view.Activity_Bet_Slips;
import com.dingar.twok.twoK.presentation.view.BetListRecyclerviewAdapter;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = BetSlipModule.class)
public interface TwoKBetSlipComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoKBetSlipComponent build();
    }

    void inject(Activity_Bet_Slips activity_bet_slips);
    void inject(BetListRecyclerviewAdapter betListRecyclerviewAdapter);
}
