package com.leeiidesu.lib.base.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeiidesu.lib.base.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.nekocode.rxlifecycle.RxLifecycle;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BaseFragment
 * Created by Leeii on 2017/11/4.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated = inflater.inflate(getLayoutResources(), container, false);
        mUnbinder = ButterKnife.bind(this, inflated);
        return inflated;
    }


    protected abstract int getLayoutResources();

    protected abstract void trySetupData(Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        trySetupData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
    }

    @Override
    public void showToast(@NonNull String message) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ToastyInstance.getInstance().showToast(activity, message);
        }
    }

    @Override
    public void showToast(int message) {
        String string = getResources().getString(message);
        showToast(string);
    }


    @Override
    public Context fetchContext() {
        return getActivity();
    }

    @Override
    public Intent fetchIntent() {
        return getActivity().getIntent();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return RxLifecycle.bind(getActivity()).withObservable();
    }
    @Override
    public <T> ObservableTransformer<T, T> bindLifecycleAndThread() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(bindLifecycle());
    }
    @Override
    public <T> ObservableTransformer<T, T> bindLifecycleAndThreadWithLoading() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(disposable -> showLoading())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(this::dismissLoading)
                        .compose(bindLifecycle());
    }
}
