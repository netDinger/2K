package com.dingar.twok.auth.di.component;

import com.dingar.twok.auth.di.module.LoginModule;
import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.presentation.view.Activity_Login;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

    @Subcomponent.Builder
    interface Builder{
        LoginComponent build ();
    }

    void inject(Activity_Login activity_login);

}
