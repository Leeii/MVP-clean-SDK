package com.leeiidesu.lib.base;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus
 * 特别注意:必须compose(RxLifecycle.bind(this).withObservable())
 * 否则会造成内存泄漏
 * Created by dgg on 2017/11/6.
 */

public class RxBus {
	private final Subject<Object> mBus;

	private RxBus() {
		mBus = PublishSubject.create().toSerialized();
	}

	public static RxBus getInstance() {
		return Holder.INSTANCE;
	}

	private static class Holder {
		private static RxBus INSTANCE = new RxBus();
	}

	public void post(@NonNull Object obj) {
		mBus.onNext(obj);
	}

	public <T> Observable<T> toObservable(Class<T> eventType) {
		return mBus.ofType(eventType);
	}

}
