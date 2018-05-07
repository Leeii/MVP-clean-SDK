package com.leeiidesu.newdagger.dagger;


import com.leeiidesu.lib.base.dagger.FragmentScope;
import com.leeiidesu.newdagger.ui.login.LoginFragment;
import com.leeiidesu.newdagger.ui.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by liyi on 2018/4/27.
 */
@Module
public abstract class FragmentBindModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    @FragmentScope
    abstract LoginFragment loginFragment();
}
