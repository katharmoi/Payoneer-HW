package io.appicenter.data.util;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import io.appicenter.domain.utils.NetworkUtils;
import io.reactivex.Observable;

public class NetworkUtilsImpl implements NetworkUtils {

    private final ConnectivityManager connectivityManager;

    public NetworkUtilsImpl(final ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean isConnected() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) return false;
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }

    @Override
    public Observable<Boolean> observeNetwork() {

        return Observable.create(emitter -> {

            NetworkRequest request = new NetworkRequest.Builder().build();

            ConnectivityManager.NetworkCallback callback = new ConnectivityManager.NetworkCallback() {

                @Override
                public void onAvailable(Network network) {
                    emitter.onNext(true);
                }

                @Override
                public void onLost(Network network) {
                    emitter.onNext(false);
                }
            };

            connectivityManager.registerNetworkCallback(request, callback);
            emitter.setCancellable(() ->
                    connectivityManager.unregisterNetworkCallback(callback)
            );
        });
    }
}
