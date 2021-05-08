package io.appicenter.payoneer.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.appicenter.domain.repository.PaymentRepository;
import io.appicenter.domain.utils.NetworkUtils;
import io.appicenter.payoneer.data.FakeNetworkUtils;
import io.appicenter.payoneer.data.FakePaymentRepository;

@Module
public class TestDataModule {

    @Provides
    @Singleton
    public PaymentRepository providePaymentRepo() {
        return new FakePaymentRepository();
    }

    @Provides
    @Singleton
    public NetworkUtils provideNetworkUtils() {
        return new FakeNetworkUtils();
    }
}
