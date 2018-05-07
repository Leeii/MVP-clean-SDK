package com.leeiidesu.newdagger.dagger;


import com.leeiidesu.lib.base.dagger.ActivityScope;
import com.leeiidesu.newdagger.ui.main.MainActivity;
import com.leeiidesu.newdagger.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by liyi on 2018/4/27.
 */

@Module
public abstract class ActivityBindModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
