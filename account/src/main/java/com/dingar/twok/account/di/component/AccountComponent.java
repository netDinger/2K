package com.dingar.twok.account.di.component;

import com.dingar.twok.account.di.module.AccountModule;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.presentation.view.Fragment_Account;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = {AccountModule.class})
public interface AccountComponent {
    @Subcomponent.Builder
    interface Builder{
        AccountComponent build();
    }

    void inject(Fragment_Account fragment_account);
}
