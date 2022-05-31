package com.dingar.twok.threeD.di.component;

import com.dingar.twok.threeD.di.module.BetSlipModule;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.presentation.view.Activity_Bet_Slips;
import com.dingar.twok.threeD.presentation.view.BetListRecyclerviewAdapter;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = BetSlipModule.class)
public interface ThreeDBetSlipComponent {

    @Subcomponent.Builder
    interface Builder{
        ThreeDBetSlipComponent build();
    }

    void inject(Activity_Bet_Slips activity_bet_slips);
    void inject(BetListRecyclerviewAdapter betListRecyclerviewAdapter);
}
