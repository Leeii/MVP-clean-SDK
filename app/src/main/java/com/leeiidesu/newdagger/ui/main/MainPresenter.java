package com.leeiidesu.newdagger.ui.main;


import com.leeiidesu.lib.base.dagger.ActivityScope;

import javax.inject.Inject;

/**
 * Created by liyi on 2018/4/27.
 */
@ActivityScope
public class MainPresenter implements MainContract.IMainPresenter {
    private MainContract.IMainView mView;

    @Inject
    MainPresenter(MainContract.IMainView mView) {
        this.mView = mView;
    }

    @Override
    public void getString() {
        mView.showToast("你要的String");
    }
}
