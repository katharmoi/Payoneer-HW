package io.appicenter.payoneer.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.appicenter.payoneer.App;
import io.appicenter.payoneer.home.HomeActivity;
import io.appicenter.payoneer.home.HomeActivityModule;

@Module
public abstract class AppModule {

    @Singleton
    @Binds
    public abstract Context provideAppContext(App app);

    @PerActivity
    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity bindMainScreen();
}
