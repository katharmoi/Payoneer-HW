package io.appicenter.data.payment.service;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.appicenter.domain.model.Links;


public final class ServiceTestData {

    public static final String CODE = "AMEX";
    public static final String LABEL = "American Express";
    public static final String METHOD = "CREDIT_CARD";


    public static final String LOGO = "https://resources.integration.oscato.com/resource/network/MOBILETEAM/en_US/AMEX/logo3x.png";
    public static final String SELF = "https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/AMEX";
    public static final String LANG = "https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/AMEX.json";

    public static final Links TEST_LINK = new Links(LOGO, SELF, LANG);
    public static final ApiPaymentMethod TEST_PAYMENT_METHOD =
            new ApiPaymentMethod(CODE, LABEL, METHOD, TEST_LINK);


}
