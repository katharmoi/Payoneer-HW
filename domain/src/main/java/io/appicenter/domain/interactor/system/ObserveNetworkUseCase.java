package io.appicenter.domain.interactor.system;

import io.appicenter.domain.interactor.type.ObservableUseCase;
import io.appicenter.domain.utils.NetworkUtils;
import io.reactivex.Observable;

/**
 * UseCase to get network info
 */
public final class ObserveNetworkUseCase implements ObservableUseCase<Boolean> {

    private final NetworkUtils networkUtils;

    public ObserveNetworkUseCase(final NetworkUtils networkUtils) {
        this.networkUtils = networkUtils;
    }

    public Observable<Boolean> apply() {
        return networkUtils.observeNetwork();
    }

}
