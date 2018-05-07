package com.leeiidesu.newdagger.domain;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by liyi on 2018/4/27.
 */
@Singleton
public class LoginUseCase {

    private DataRepository repository;

    @Inject
    public LoginUseCase(DataRepository repository) {
        this.repository = repository;
    }

    public String execute(String string, String string1) {
        return repository.append(String.format("LoginUseCase:  username = %s,password = %s", string, string1));
    }
}
