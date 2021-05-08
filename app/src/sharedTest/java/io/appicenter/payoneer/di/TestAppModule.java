package io.appicenter.payoneer.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.appicenter.domain.interactor.payment.GetPaymentMethodsUseCase;
import io.appicenter.domain.interactor.system.ObserveNetworkUseCase;
import io.appicenter.domain.repository.PaymentRepository;
import io.appicenter.domain.utils.NetworkUtils;
import io.appicenter.payoneer.home.HomeActivity;
import io.appicenter.payoneer.payment.PaymentMethodsFragment;

@Module
public abstract class TestAppModule {

    @Singleton
    @Binds
    public abstract Context provideAppContext(TestApp app);

    @PerActivity
    @ContributesAndroidInjector(modules = {TestHomeActivityModule.class})
    public abstract HomeActivity bindHomeScreen();

    @PerActivity
    @ContributesAndroidInjector
    public abstract PaymentMethodsFragment paymentMethodsScreenInjector();

    @Singleton
    @Provides
    public static GetPaymentMethodsUseCase provideGetPaymentMethodsUseCase(PaymentRepository paymentRepository) {
        return new GetPaymentMethodsUseCase(paymentRepository);
    }

    @Singleton
    @Provides
    public static ObserveNetworkUseCase provideObserveNetworkUseCase(NetworkUtils networkUtils) {
        return new ObserveNetworkUseCase(networkUtils);
    }

}
