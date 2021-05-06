package io.appicenter.payoneer.home;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.appicenter.domain.interactor.payment.GetPaymentMethodsUseCase;
import io.appicenter.domain.interactor.system.ObserveNetworkUseCase;
import io.appicenter.domain.repository.PaymentRepository;
import io.appicenter.domain.utils.NetworkUtils;
import io.appicenter.payoneer.di.PerActivity;
import io.appicenter.payoneer.di.PerFragment;
import io.appicenter.payoneer.payment.PaymentMethodsFragment;

@Module
public abstract class HomeActivityModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract PaymentMethodsFragment homeScreenInjector();

    @PerActivity
    @Provides
    public static GetPaymentMethodsUseCase provideGetPaymentMethodsUseCase(PaymentRepository paymentRepository) {
        return new GetPaymentMethodsUseCase(paymentRepository);
    }

    @PerActivity
    @Provides
    public static ObserveNetworkUseCase provideObserveNetworkUseCase(NetworkUtils networkUtils) {
        return new ObserveNetworkUseCase(networkUtils);
    }
}
