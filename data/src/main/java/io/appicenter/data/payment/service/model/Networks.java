package io.appicenter.data.payment.service.model;

import java.util.List;

public class Networks {
    public final List<ApiPaymentMethod> applicable;

    public Networks(final List<ApiPaymentMethod> applicable) {
        this.applicable = applicable;
    }

    @Override
    public String toString() {
        return "Networks{" +
                "applicable=" + applicable +
                '}';
    }
}
