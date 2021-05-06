package io.appicenter.payoneer.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.appicenter.domain.interactor.system.ObserveNetworkUseCase;
import io.appicenter.payoneer.di.SchedulersModule;
import io.reactivex.Scheduler;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final Scheduler mainScheduler;
    private final ObserveNetworkUseCase observeNetworkUseCase;

    @Inject
    public HomeViewModelFactory(final ObserveNetworkUseCase observeNetworkUseCase,
                                final @Named(SchedulersModule.MAIN_THREAD_SCHEDULER) Scheduler mainScheduler) {
        this.observeNetworkUseCase = observeNetworkUseCase;
        this.mainScheduler = mainScheduler;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {

            return (T) new HomeViewModel(
                    observeNetworkUseCase,
                    mainScheduler

            );
        } else throw new IllegalArgumentException("Unknown View Model Class");
    }
}
