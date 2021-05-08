package io.appicenter.payoneer.di;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class TestApp extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerTestAppComponent.builder().create(this);
    }
}
