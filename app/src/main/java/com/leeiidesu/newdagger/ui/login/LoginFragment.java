package com.leeiidesu.newdagger.ui.login;

import android.os.Bundle;
import android.widget.EditText;

import com.leeiidesu.newdagger.R;
import com.leeiidesu.newdagger.common.DaggerFragment;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liyi on 2018/4/27.
 */
public class LoginFragment extends DaggerFragment implements LoginContract.ILoginView {
    @Inject
    LoginContract.ILoginPresenter mPresenter;
    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.pwd)
    EditText mPwd;

    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_login;
    }

    @Override
    protected void trySetupData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        mPresenter.login(mUserName.getText().toString(), mPwd.getText().toString());
    }
}
