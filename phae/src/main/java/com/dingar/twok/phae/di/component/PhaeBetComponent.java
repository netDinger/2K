package com.dingar.twok.phae.di.component;

import com.dingar.twok.phae.di.module.DiceBetModule;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.presentation.view.Activity_DiceBet;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = DiceBetModule.class)
public interface PhaeBetComponent {

    @Subcomponent.Builder
    interface Builder{
        PhaeBetComponent build();
    }

    void inject(Activity_DiceBet diceBet);
}
