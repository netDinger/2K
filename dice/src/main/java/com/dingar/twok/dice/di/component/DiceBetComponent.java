package com.dingar.twok.dice.di.component;

import com.dingar.twok.dice.di.module.DiceBetModule;
import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.presentation.view.Activity_DiceBet;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = DiceBetModule.class)
public interface DiceBetComponent {

    @Subcomponent.Builder
    interface Builder{
        DiceBetComponent build();
    }

    void inject(Activity_DiceBet diceBet);
}
