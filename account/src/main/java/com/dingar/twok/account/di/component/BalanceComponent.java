package com.dingar.twok.account.di.component;

import com.dingar.twok.account.di.module.BalanceModule;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.presentation.view.Activity_Balance;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = {BalanceModule.class})
public interface BalanceComponent {
    @Subcomponent.Builder
    interface Builder{
        BalanceComponent build();
    }

    void inject(Activity_Balance activity_balance);

}
