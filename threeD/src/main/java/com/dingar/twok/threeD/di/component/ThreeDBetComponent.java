package com.dingar.twok.threeD.di.component;

import com.dingar.twok.threeD.di.module.DiceBetModule;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.presentation.view.Activity_DiceBet;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = DiceBetModule.class)
public interface ThreeDBetComponent {

    @Subcomponent.Builder
    interface Builder{
        ThreeDBetComponent build();
    }

    void inject(Activity_DiceBet diceBet);
}
