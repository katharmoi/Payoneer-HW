package io.appicenter.data.payment.service;

import java.util.List;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.reactivex.Single;

public final class PaymentServiceImpl implements PaymentService {

    private final PaymentApi paymentAPI;

    public PaymentServiceImpl(final PaymentApi paymentAPI) {
        this.paymentAPI = paymentAPI;
    }

    @Override
    public Single<List<ApiPaymentMethod>> getPaymentMethods() {
        return paymentAPI.getPaymentMethods().map(response -> response.networks.applicable);
    }
}
