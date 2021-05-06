package io.appicenter.domain.model;

import java.util.Objects;

/**
 * POJO class to hold payment method data
 */
public final class PaymentMethod {

    public final String code;
    public final String label;
    public final String method;
    public final Links links;

    public PaymentMethod(final String code, final String label, final String method, final Links links) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(label, that.label) &&
                Objects.equals(method, that.method) &&
                Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, label, method, links);
    }
}
