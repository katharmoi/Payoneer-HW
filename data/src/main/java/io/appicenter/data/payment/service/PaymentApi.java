package io.appicenter.data.payment.service;

import io.appicenter.data.payment.service.model.Networks;
import io.appicenter.data.payment.service.model.Response;
import io.appicenter.data.util.NetworkConfig;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface PaymentApi {

    /**
     * Get payment method data from remote
     */
    @GET(NetworkConfig.DISCOVER_PAYMENT_METHODS_ENDPOINT)
    Single<Response> getPaymentMethods();
}
