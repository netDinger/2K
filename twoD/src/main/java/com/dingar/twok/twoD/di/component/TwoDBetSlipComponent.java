package com.dingar.twok.twoD.di.component;

import com.dingar.twok.twoD.di.module.BetSlipModule;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.presentation.view.Activity_Bet_Slips;
import com.dingar.twok.twoD.presentation.view.BetListRecyclerviewAdapter;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = BetSlipModule.class)
public interface TwoDBetSlipComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoDBetSlipComponent build();
    }

    void inject(Activity_Bet_Slips activity_bet_slips);
    void inject(BetListRecyclerviewAdapter betListRecyclerviewAdapter);
}
