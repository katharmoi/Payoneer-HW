package io.appicenter.payoneer.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {
    private static final String MAIN_THREAD_SCHEDULER = "mainScheduler";
    private static final String BG_SCHEDULER = "bgScheduler";

    @Singleton
    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    @Named(BG_SCHEDULER)
    public Scheduler provideBGScheduler() {
        return Schedulers.io();
    }
}
