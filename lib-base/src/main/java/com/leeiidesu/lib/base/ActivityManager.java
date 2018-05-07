package com.leeiidesu.lib.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by dgg on 2017/11/7.
 */

public class ActivityManager {
	private Stack<Activity> mActivityStack = new Stack<>();

	public static ActivityManager getInstance() {
		return Holder.INSTANCE;
	}

	public void push(Activity activity) {
		mActivityStack.push(activity);
	}

	public Activity pop() {
		return mActivityStack.pop();
	}

	public Activity peek() {
		return mActivityStack.peek();
	}

	public int search(Object obj) {
		return mActivityStack.search(obj);
	}

	public boolean remove(Activity activity) {
		return mActivityStack.remove(activity);
	}

	public void finishAll() {
		for (Activity a :
				mActivityStack) {
			a.finish();
		}
	}


	private static class Holder {
		private static ActivityManager INSTANCE = new ActivityManager();
	}
}
