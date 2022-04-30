package com.dingar.twok.dice.di.component;

import com.dingar.twok.dice.di.module.BetSlipModule;
import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.presentation.view.Activity_Bet_Slips;
import com.dingar.twok.dice.presentation.view.BetListRecyclerviewAdapter;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = BetSlipModule.class)
public interface BetSlipComponent {

    @Subcomponent.Builder
    interface Builder{
        BetSlipComponent build();
    }

    void inject(Activity_Bet_Slips activity_bet_slips);
    void inject(BetListRecyclerviewAdapter betListRecyclerviewAdapter);
}
