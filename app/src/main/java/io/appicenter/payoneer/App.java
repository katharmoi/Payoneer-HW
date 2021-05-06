package io.appicenter.payoneer;

import org.jetbrains.annotations.NotNull;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.appicenter.data.BuildConfig;
import io.appicenter.payoneer.di.DaggerAppComponent;
import timber.log.Timber;

public class App extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree() {
                @Override
                public String createStackElementTag(@NotNull StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerAppComponent.builder().create(this);
    }
}
