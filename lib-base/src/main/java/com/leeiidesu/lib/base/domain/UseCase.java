package com.leeiidesu.lib.base.domain;


import io.reactivex.Observable;

/**
 * Created by dgg on 2017/11/3.
 */

public interface UseCase<T> {
	Observable<T> execute();
}
