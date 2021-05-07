package io.appicenter.payoneer.payment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.appicenter.domain.interactor.payment.GetPaymentMethodsUseCase;
import io.appicenter.payoneer.di.PerActivity;
import io.appicenter.payoneer.di.SchedulersModule;
import io.reactivex.Scheduler;


@PerActivity
public class PaymentViewModelFactory implements ViewModelProvider.Factory {

    private final Scheduler bgScheduler;
    private final Scheduler mainScheduler;
    private final GetPaymentMethodsUseCase getPaymentMethodsUseCase;

    @Inject
    public PaymentViewModelFactory(final GetPaymentMethodsUseCase getPaymentMethodsUseCase,
                                   final @Named(SchedulersModule.BG_SCHEDULER) Scheduler bgScheduler,
                                   final @Named(SchedulersModule.MAIN_THREAD_SCHEDULER) Scheduler mainScheduler) {
        this.getPaymentMethodsUseCase = getPaymentMethodsUseCase;
        this.mainScheduler = mainScheduler;
        this.bgScheduler = bgScheduler;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PaymentViewModel.class)) {

            return (T) new PaymentViewModel(
                    getPaymentMethodsUseCase,
                    bgScheduler,
                    mainScheduler

            );
        } else throw new IllegalArgumentException("Unknown View Model Class");
    }
}
