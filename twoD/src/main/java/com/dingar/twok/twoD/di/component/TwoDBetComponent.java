package com.dingar.twok.twoD.di.component;

import com.dingar.twok.twoD.di.module.DiceBetModule;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.presentation.view.Activity_DiceBet;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = DiceBetModule.class)
public interface TwoDBetComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoDBetComponent build();
    }

    void inject(Activity_DiceBet diceBet);
}
