package com.leeiidesu.lib.base.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 该注解代表被注解的对象在Fragment生命周期有效
 * Created by Lee on 2016/12/23.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
