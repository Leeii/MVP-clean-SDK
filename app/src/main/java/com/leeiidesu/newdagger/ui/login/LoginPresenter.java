package com.leeiidesu.newdagger.ui.login;


import com.leeiidesu.lib.base.dagger.FragmentScope;
import com.leeiidesu.newdagger.domain.LoginUseCase;

import javax.inject.Inject;

/**
 * Created by liyi on 2018/4/27.
 */
@FragmentScope
public class LoginPresenter implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginView mView;
    private LoginUseCase loginUseCase;

    @Inject
    public LoginPresenter(LoginContract.ILoginView mView, LoginUseCase loginUseCase) {
        this.mView = mView;
        this.loginUseCase = loginUseCase;
    }

    @Override
    public void login(String string, String string1) {

        mView.showToast(loginUseCase.execute(string, string1));
    }
}
