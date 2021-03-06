package com.leeiidesu.lib.base.loading.view;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by dgg on 2017/11/7.
 */

public class OriginView implements IStatusView {
	private View contentView;

	public OriginView(@NonNull View contentView) {
		this.contentView = contentView;
	}

	@Override
	public View getView() {
		return contentView;
	}

	@Override
	public void setTag(int tag) {

	}

	@Override
	public int getTag() {
		return Integer.MIN_VALUE;
	}


	@Override
	public void setMessage(String message) {

	}

	@Override
	public void setButtonText(String button) {

	}

	@Override
	public void startAnimation() {

	}

	@Override
	public void stopAnimation() {

	}

	@Override
	public void destroy() {
		contentView = null;
	}
}
