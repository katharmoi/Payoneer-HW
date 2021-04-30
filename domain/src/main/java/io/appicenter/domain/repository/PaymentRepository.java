package io.appicenter.domain.repository;

import java.util.List;

import io.appicenter.domain.model.PaymentMethod;
import io.reactivex.Single;

public interface PaymentRepository {

    Single<List<PaymentMethod>> getPaymentMethods();
}
