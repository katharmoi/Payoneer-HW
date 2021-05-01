package io.appicenter.domain.interactor;

import io.appicenter.domain.model.Links;
import io.appicenter.domain.model.PaymentMethod;


public final class DomainTestData {

    public static final String CODE = "SEPADD";
    public static final String LABEL = "SEPA";
    public static final String METHOD = "DIRECT_DEBIT";


    public static final String LOGO = "https://abcd.com/logo.png";
    public static final String SELF = "https://xyzt.com/SEPADD";
    public static final String LANG = "https://xyzt.com/SEPADD.json";

    public static final Links TEST_LINK = new Links(LOGO, SELF, LANG);
    public static final PaymentMethod TEST_PAYMENT_METHOD =
            new PaymentMethod(CODE, LABEL, METHOD, TEST_LINK);


}
