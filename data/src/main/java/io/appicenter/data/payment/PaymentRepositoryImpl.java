package io.appicenter.data.payment;

import java.util.List;

import io.appicenter.data.payment.service.PaymentService;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.domain.repository.PaymentRepository;
import io.reactivex.Single;

public final class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentService paymentService;

    public PaymentRepositoryImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Single<List<PaymentMethod>> getPaymentMethods() {
        return null;
    }
}
