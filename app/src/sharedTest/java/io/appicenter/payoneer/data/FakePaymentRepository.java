package io.appicenter.payoneer.data;

import java.util.ArrayList;
import java.util.List;

import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.domain.repository.PaymentRepository;
import io.reactivex.Single;

public class FakePaymentRepository implements PaymentRepository {
    public static List<PaymentMethod> result;

    @Override
    public Single<List<PaymentMethod>> getPaymentMethods() {
        return Single.just(new ArrayList<>());
    }
}
