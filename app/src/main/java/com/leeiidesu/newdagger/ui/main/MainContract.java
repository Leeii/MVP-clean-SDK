package com.leeiidesu.newdagger.ui.main;


import com.leeiidesu.lib.base.mvp.BasePresenter;
import com.leeiidesu.lib.base.mvp.BaseView;

/**
 * Created by liyi on 2018/4/27.
 */
public interface MainContract {
    interface IMainView extends BaseView {

    }

    interface IMainPresenter extends BasePresenter {

        void getString();
    }
}
