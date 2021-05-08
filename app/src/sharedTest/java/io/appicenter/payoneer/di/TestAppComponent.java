package io.appicenter.payoneer.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                TestAppModule.class,
                TestSchedulersModule.class,
                TestDataModule.class
        }
)
interface TestAppComponent extends AndroidInjector<TestApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TestApp> {
    }
}
