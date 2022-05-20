package com.dingar.twok.history.di.component;

import com.dingar.twok.history.di.module.HistoryModule;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.presentation.view.Fragment_History;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent (modules = {HistoryModule.class})
public interface HistoryComponent {

    @Subcomponent.Builder
    interface Builder{
        HistoryComponent build();
    }

    void inject(Fragment_History fragment_history);
}
