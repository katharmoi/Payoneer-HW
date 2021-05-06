package io.appicenter.domain.utils;

import io.reactivex.Observable;

public interface NetworkUtils {

    boolean isConnected();
    Observable<Boolean> observeNetwork();
}
