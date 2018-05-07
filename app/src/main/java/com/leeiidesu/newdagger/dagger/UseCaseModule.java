package com.leeiidesu.newdagger.dagger;


import com.leeiidesu.newdagger.domain.DataRepository;
import com.leeiidesu.newdagger.domain.LoginUseCase;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Created by liyi on 2018/4/27.
 */
@Module
public abstract class UseCaseModule {
    @Singleton
    LoginUseCase loginUseCase(DataRepository repository) {
        return new LoginUseCase(repository);
    }
}
