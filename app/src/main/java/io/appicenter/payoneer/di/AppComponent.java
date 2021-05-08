package io.appicenter.payoneer.di;

import androidx.annotation.VisibleForTesting;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.appicenter.payoneer.App;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        DataModule.class,
        SchedulersModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }

    @VisibleForTesting
    OkHttpClient getOkHttpClient();
}
