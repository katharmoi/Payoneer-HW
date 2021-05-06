package io.appicenter.domain.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Links links = (Links) o;
        return Objects.equals(logo, links.logo) &&
                Objects.equals(self, links.self) &&
                Objects.equals(lang, links.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logo, self, lang);
    }
}
