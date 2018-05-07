package com.leeiidesu.lib.base.loading;

import android.content.Context;
import android.support.annotation.Nullable;

import com.leeiidesu.lib.base.loading.callback.OnRetryClickListener;
import com.leeiidesu.lib.base.loading.view.IStatusView;

/**
 * Created by dgg on 2017/11/7.
 */

public interface StatusViewCreator {
	IStatusView onCreateStatusView(Context context, Status status, @Nullable OnRetryClickListener l);
}
