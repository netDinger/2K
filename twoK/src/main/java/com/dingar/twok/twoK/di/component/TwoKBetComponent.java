package com.dingar.twok.twoK.di.component;

import com.dingar.twok.twoK.di.module.DiceBetModule;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.presentation.view.Activity_DiceBet;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = DiceBetModule.class)
public interface TwoKBetComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoKBetComponent build();
    }

    void inject(Activity_DiceBet diceBet);
}
