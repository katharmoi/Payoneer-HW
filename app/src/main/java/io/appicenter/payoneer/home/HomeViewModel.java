package io.appicenter.payoneer.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.appicenter.domain.interactor.system.ObserveNetworkUseCase;
import io.appicenter.payoneer.base.BaseViewModel;
import io.reactivex.Scheduler;
import timber.log.Timber;

public class HomeViewModel extends BaseViewModel {

    private final ObserveNetworkUseCase observeNetworkUseCase;
    private final Scheduler mainScheduler;

    public HomeViewModel(
            ObserveNetworkUseCase observeNetworkUseCase,
            Scheduler mainScheduler) {
        this.observeNetworkUseCase = observeNetworkUseCase;
        this.mainScheduler = mainScheduler;

        observeNetwork();
    }

    private final MutableLiveData<Boolean> _networkState = new MutableLiveData<>();
    public final LiveData<Boolean> networkState = _networkState;


    public void observeNetwork() {
        disposables.add(
                observeNetworkUseCase.apply()
                        .observeOn(mainScheduler)
                        .subscribe(
                                _networkState::setValue,
                                Timber::e
                        )

        );
    }


}
