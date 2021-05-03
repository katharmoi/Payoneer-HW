package io.appicenter.payoneer.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import io.appicenter.payoneer.App;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
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
}
