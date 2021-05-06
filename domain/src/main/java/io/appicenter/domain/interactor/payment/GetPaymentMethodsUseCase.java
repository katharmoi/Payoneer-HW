package io.appicenter.domain.interactor.payment;

import java.util.List;

import io.appicenter.domain.interactor.type.SingleUseCase;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.domain.repository.PaymentRepository;
import io.reactivex.Single;

/**
 * UseCase to get payment methods
 */
public final class GetPaymentMethodsUseCase implements SingleUseCase<List<PaymentMethod>> {

    private final PaymentRepository paymentRepository;

    public GetPaymentMethodsUseCase(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Single<List<PaymentMethod>> apply() {
        return paymentRepository.getPaymentMethods();
    }
}
