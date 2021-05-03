package io.appicenter.payoneer.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import io.appicenter.payoneer.App;

@Module
public abstract class AppModule {

    @Singleton
    @Binds
    public abstract Context provideAppContext(App app);
}
