package io.appicenter.data.payment.adapters;

import javax.inject.Inject;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.appicenter.domain.model.PaymentMethod;

public final class PaymentMethodModelAdapter {

    @Inject
    public PaymentMethodModelAdapter() {
    }

    public PaymentMethod dataToDomain(final ApiPaymentMethod dataModel) {
        return new PaymentMethod(
                dataModel.code,
                dataModel.label,
                dataModel.method,
                dataModel.links
        );
    }

    public ApiPaymentMethod domainToData(final PaymentMethod domainData) {
        return new ApiPaymentMethod(
                domainData.code,
                domainData.label,
                domainData.method,
                domainData.links
        );
    }
}
