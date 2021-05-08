package io.appicenter.payoneer.data;

import io.appicenter.domain.utils.NetworkUtils;
import io.reactivex.Observable;

public class FakeNetworkUtils implements NetworkUtils {

    public static boolean connected;

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public Observable<Boolean> observeNetwork() {
        return Observable.just(connected);
    }
}
