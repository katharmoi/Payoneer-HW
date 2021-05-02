package io.appicenter.data.payment.service.model;

import io.appicenter.domain.model.Links;

public final class ApiPaymentMethod {

    public final String code;
    public final String label;
    public final String method;
    public final Links links;

    public ApiPaymentMethod(final String code, final String label, final String method, final Links links) {
        this.code = code;
        this.label = label;
        this.method = method;
        this.links = links;
    }


    @Override
    public String toString() {
        return "PaymentMethod{" +
                "code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", method='" + method + '\'' +
                ", links=" + links +
                '}';
    }
}
