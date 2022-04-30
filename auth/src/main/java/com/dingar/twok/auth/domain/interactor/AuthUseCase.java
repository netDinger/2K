package com.dingar.twok.auth.domain.interactor;


import com.dingar.twok.auth.domain.repository.AuthRepository;

import javax.inject.Inject;

public class AuthUseCase {
    @Inject
    AuthRepository authRepository;

    @Inject
    public AuthUseCase(){}

    public boolean isAuthenticated(){
        return authRepository.isAuthenticated();
    }
}
