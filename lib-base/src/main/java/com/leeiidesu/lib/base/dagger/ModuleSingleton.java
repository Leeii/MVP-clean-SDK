package com.leeiidesu.lib.base.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 该注解代表被注解的对象 在Module的生命周期是单例
 * 区别 @Singleton 代表全局单例
 * Created by dgg on 2017/11/10.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleSingleton {
}
