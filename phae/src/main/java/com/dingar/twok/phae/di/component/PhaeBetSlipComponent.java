package com.dingar.twok.phae.di.component;

import com.dingar.twok.phae.di.module.BetSlipModule;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.presentation.view.Activity_Bet_Slips;
import com.dingar.twok.phae.presentation.view.BetListRecyclerviewAdapter;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = BetSlipModule.class)
public interface PhaeBetSlipComponent {

    @Subcomponent.Builder
    interface Builder{
        PhaeBetSlipComponent build();
    }

    void inject(Activity_Bet_Slips activity_bet_slips);
    void inject(BetListRecyclerviewAdapter betListRecyclerviewAdapter);
}
