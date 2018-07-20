package com.leeiidesu.lib.base.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.leeiidesu.lib.base.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.nekocode.rxlifecycle.LifecycleEvent;
import cn.nekocode.rxlifecycle.RxLifecycle;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BaseActivity
 * Created by dgg on 2017/11/2.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeAddView();
        setContentView(getLayoutResources());
        unbinder = ButterKnife.bind(this);
        trySetupData(savedInstanceState);
    }

    protected abstract void trySetupData(Bundle savedInstanceState);

    protected abstract int getLayoutResources();

    protected void beforeAddView() {
    }

    @Override
    public void showToast(@NonNull String message) {
        ToastyInstance.getInstance().showToast(this, message);
    }

    @Override
    public void showToast(int message) {
        String string = getResources().getString(message);
        ToastyInstance.getInstance().showToast(this, string);
    }

    @Override
    public Context fetchContext() {
        return this;
    }

    @Override
    public Intent fetchIntent() {
        return getIntent();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return RxLifecycle.bind(this).disposeObservableWhen(LifecycleEvent.DESTROY);
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
