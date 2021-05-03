package io.appicenter.data.payment.service;

import java.util.List;

import javax.inject.Inject;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.reactivex.Single;

public final class PaymentServiceImpl implements PaymentService {

    private final PaymentApi paymentAPI;

    @Inject
    public PaymentServiceImpl(final PaymentApi paymentAPI) {
        this.paymentAPI = paymentAPI;
    }

    @Override
    public Single<List<ApiPaymentMethod>> getPaymentMethods() {
        return paymentAPI.getPaymentMethods().map(response -> response.networks.applicable);
    }
}
