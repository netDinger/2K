package com.dingar.twok.auth.data.repository;

import com.dingar.twok.auth.domain.repository.AuthRepository;

import javax.inject.Inject;

public class AuthRepositoryImpl implements AuthRepository {
    @Inject
    public AuthRepositoryImpl(){}

    @Override
    public boolean isAuthenticated() {
        return com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser()!=null;
    }
}
