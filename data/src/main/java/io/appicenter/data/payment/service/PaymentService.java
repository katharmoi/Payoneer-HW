package io.appicenter.data.payment.service;

import java.util.List;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.reactivex.Single;

public interface PaymentService {

    Single<List<ApiPaymentMethod>> getPaymentMethods();
}
