 package io.appicenter.data.payment;

import java.util.ArrayList;
import java.util.List;

import io.appicenter.data.payment.adapters.PaymentMethodModelAdapter;
import io.appicenter.data.payment.service.PaymentService;
import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.domain.repository.PaymentRepository;
import io.reactivex.Single;

public final class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentService paymentService;
    private final PaymentMethodModelAdapter adapter;

    public PaymentRepositoryImpl(final PaymentService paymentService, final PaymentMethodModelAdapter adapter) {
        this.paymentService = paymentService;
        this.adapter = adapter;
    }

    @Override
    public Single<List<PaymentMethod>> getPaymentMethods() {

        return paymentService.getPaymentMethods().map(
                result -> {
                    List<PaymentMethod> list = new ArrayList<>();
                    for (ApiPaymentMethod pm : result) list.add(adapter.dataToDomain(pm));
                    return list;
                });
    }
}
