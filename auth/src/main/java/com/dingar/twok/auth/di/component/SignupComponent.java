package com.dingar.twok.auth.di.component;

import com.dingar.twok.auth.di.module.SignupModule;
import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.presentation.view.Activity_Code_Verification;
import com.dingar.twok.auth.presentation.view.Activity_Signup;

import dagger.Subcomponent;

/**
 * for educational purpose, this module use @link Subcomponent.Factory*/
@FeatureScope
@Subcomponent(modules = SignupModule.class)
public interface SignupComponent {

    @Subcomponent.Factory
    interface Factory{
        SignupComponent create();
    }

    void inject(Activity_Signup activity_signup);
}
