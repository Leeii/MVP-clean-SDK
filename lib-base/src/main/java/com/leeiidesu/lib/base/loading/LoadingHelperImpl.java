package com.leeiidesu.lib.base.loading;

import android.content.Context;
import android.view.View;

import com.leeiidesu.lib.base.R;
import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.change.SwitchLayoutHelper;
import com.leeiidesu.lib.base.loading.view.IStatusView;

import java.util.HashMap;

/**
 * 实际实现
 * Created by leeiides on 2017/6/23.
 */

class LoadingHelperImpl extends LoadingHelper {

    private StatusViewCreator mDefaultStatusViewCreator;

    private Context context;

    private View originView;
    private SwitchLayoutHelper mSwitchLayoutHelper;

    private HashMap<Status, IStatusView> mViewMap = new HashMap<>();
    private OnRetryClickListener mOnRetryClickListener;
    private Status currentStatus;


    LoadingHelperImpl(IStatusView originView,
                      SwitchLayoutHelper switchLayout,
                      StatusViewCreator mDefaultStatusViewCreator) {
        this.mDefaultStatusViewCreator = mDefaultStatusViewCreator;
        this.originView = originView.getView();
        context = this.originView.getContext();
        this.mSwitchLayoutHelper = switchLayout;

        mViewMap.put(Status.NORMAL, originView);
        originView.getView().setTag(R.id.status_layout_origin_helper, this);
        currentStatus = Status.NORMAL;
    }

    @Override
    public void showLoading(String message) {
        show(Status.LOADING, message, null);
    }

    @Override
    public void showEmpty(String message, String button) {
        show(Status.EMPTY, message, button);
    }

    @Override
    public void showError(String message, String button) {
        show(Status.ERROR, message, button);
    }

    @Override
    public void restore() {
        show(Status.NORMAL, null, null);
    }


    @Override
    public void showNoNetwork(String message, String button) {
        show(Status.NETWORK, message, button);
    }

    private void putCustomViewByStatus(Status status, IStatusView statusView) {
        mViewMap.put(status, statusView);
    }

    @Override
    public void removeAllView() {
        mViewMap.clear();
        mViewMap = null;
        originView = null;
        mSwitchLayoutHelper.removeAllViews();
        mSwitchLayoutHelper = null;
    }

    @Override
    public void setOnRetryClickListener(OnRetryClickListener l) {
        this.mOnRetryClickListener = l;
    }

    @Override
    public void putCustomView(IStatusView view) {
        mViewMap.put(Status.CUSTOM, view);
    }

    @Override
    public void showCustom(String message, String button) {
        show(Status.CUSTOM, message, button);
    }

    @Override
    public void show(Status status, String message, String button) {
        currentStatus = status;
        IStatusView statusView = mViewMap.get(status);

        if (statusView == null) {
            statusView = mDefaultStatusViewCreator.onCreateStatusView(context, status, mOnRetryClickListener);
            putCustomViewByStatus(status, statusView);
        }
        statusView.setMessage(message);
        statusView.setButtonText(button);
        stopAnimationIfExist(mSwitchLayoutHelper.getCurrentStatusView());
        startAnimationIfExist(statusView);

        mSwitchLayoutHelper.switchLayout(statusView);
    }

    @Override
    public boolean isRestored() {
        return currentStatus == Status.NORMAL;
    }

    private void stopAnimationIfExist(IStatusView currentView) {
        currentView.stopAnimation();
    }

    private void startAnimationIfExist(IStatusView currentView) {
        currentView.startAnimation();
    }
}
