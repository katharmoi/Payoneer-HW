package io.appicenter.domain.model;

/**
 * POJO class to hold payment method links data
 */
public final class Links {

    public final String logo;
    public final String self;
    public final String lang;

    public Links(final String logo, final String self, final String lang) {
        this.logo = logo;
        this.self = self;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Links{" +
                "logo='" + logo + '\'' +
                ", self='" + self + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
