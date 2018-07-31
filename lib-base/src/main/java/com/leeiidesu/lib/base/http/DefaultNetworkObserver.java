package com.leeiidesu.lib.base.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.leeiidesu.lib.base.loading.LoadingHelper;

import com.leeiidesu.lib.base.common.ToastyInstance;
import com.leeiidesu.lib.core.android.Logger;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 默认网络订阅
 * Created by dgg on 2017/11/7.
 */
public abstract class DefaultNetworkObserver<T> implements Observer<T> {
    private final String TAG = "DefaultNetworkObserver";
    private IErrorDialog alert;
    private Context context;
    private LoadingHelper helper;

    private final MODE mode;

    protected DefaultNetworkObserver(@NonNull Context context) {
        this.context = context;
        this.mode = MODE.TOAST;
    }

    protected DefaultNetworkObserver() {
        this.mode = MODE.DEFAULT;
    }

    protected DefaultNetworkObserver(@NonNull IErrorDialog dialog) {
        this.mode = MODE.ALERT;
        this.alert = dialog;
    }

    protected DefaultNetworkObserver(@NonNull LoadingHelper helper) {
        this.mode = MODE.REPLACE;
        this.helper = helper;
    }


    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        String errmsg = processException(t);
        if (!TextUtils.isEmpty(errmsg))
            handlerException(errmsg);
    }

    /**
     * 处理异常
     *
     * @param errorMsg 异常信息
     */
    protected void handlerException(String errorMsg) {
        switch (mode) {
            case DEFAULT:
                //静默不做任何处理
                break;
            case REPLACE:
                helper.showError(errorMsg);
                break;
            case TOAST:
                ToastyInstance.getInstance().showToast(context, errorMsg);
                break;
            case ALERT:
                alert.show(errorMsg);
                break;
            case LOGGER:
                Logger.e(TAG, errorMsg);
                break;
            default:
                //noting to do
                Logger.e(TAG, errorMsg);//所有type都输出日志
                break;
        }
    }

    private String processException(Throwable t) {
        String errMsg;
        if (t instanceof SocketTimeoutException) {
            errMsg = "连接超时";
        } else if (t instanceof ConnectException) {
            errMsg = "网络连接失败";
        } else if (t instanceof UnknownHostException) {
            errMsg = "找不到服务器";
        } else if (t instanceof HttpException) {
            errMsg = "服务器开小差啦";
        } else if (t instanceof JSONException) {
            errMsg = "数据解析失败";
        } else {
            errMsg = t.getMessage();
        }

        return errMsg;
    }

    @Override
    public void onComplete() {

    }
}
