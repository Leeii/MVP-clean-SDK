package com.leeiidesu.lib.base.loading.change;

import android.support.annotation.NonNull;

import com.leeiidesu.lib.base.loading.view.IStatusView;

/**
 * Created by leeii on 2017/6/23.
 */

public interface SwitchLayoutHelper {
    void switchLayout(@NonNull IStatusView targetView);

    void removeAllViews();

    @NonNull
    IStatusView getCurrentStatusView();
}
