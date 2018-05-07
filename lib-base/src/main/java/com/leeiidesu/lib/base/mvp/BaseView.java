package com.leeiidesu.lib.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;

import io.reactivex.ObservableTransformer;

/**
 * 基类View
 * Created by dgg on 2017/11/14.
 */

public interface BaseView {
    /**
     * 弹出Toast
     *
     * @param message 消息
     */
    void showToast(String message);

    /**
     * 弹出Toast
     *
     * @param message 消息stringId
     */
    void showToast(@StringRes int message);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context fetchContext();

    /**
     * 获取上一个页面传入的Intent
     *
     * @return getIntent()
     */
    Intent fetchIntent();

    /**
     * 弹出一个加载中的dialog
     */
    void showLoading();

    /**
     * 隐藏加载中的dialog
     */
    void dismissLoading();

    /**
     * 结束界面
     */
    void finishActivity();

    /**
     * 绑定RxJava生命周期
     */
    <T> ObservableTransformer<T, T> bindLifecycle();

    /**
     * 绑定RxJava生命周期和线程调度
     */
    <T> ObservableTransformer<T, T> bindLifecycleAndThread();

    /**
     * 绑定RxJava生命周期和线程调度并弹出loading对话框
     */
    <T> ObservableTransformer<T, T> bindLifecycleAndThreadWithLoading();
}
