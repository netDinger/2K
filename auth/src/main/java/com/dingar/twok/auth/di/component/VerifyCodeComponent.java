package com.dingar.twok.auth.di.component;

import com.dingar.twok.auth.di.module.VerifyCodeModule;
import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.presentation.view.Activity_Code_Verification;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = {VerifyCodeModule.class})
public interface VerifyCodeComponent {

    @Subcomponent.Factory
    interface Factory{
        VerifyCodeComponent create();
    }

    void inject(Activity_Code_Verification activity_code_verification);

}
