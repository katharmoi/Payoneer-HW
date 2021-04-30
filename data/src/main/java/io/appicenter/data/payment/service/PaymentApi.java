package io.appicenter.data.payment.service;

import java.util.List;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface PaymentApi {

    /**
     * Get payment method data from remote
     */
    @GET("listresult.json")
    Single<List<ApiPaymentMethod>> getPaymentMethods();
}
