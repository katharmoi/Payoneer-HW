package io.appicenter.domain.interactor.payment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.appicenter.domain.interactor.DomainTestData;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.domain.repository.PaymentRepository;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

class GetPaymentMethodsUseCaseTest {

    private GetPaymentMethodsUseCase underTest;
    private PaymentRepository paymentRepository;
    private final TestObserver<List<PaymentMethod>> testSubscriber = new TestObserver<>();

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        underTest = new GetPaymentMethodsUseCase(paymentRepository);
    }

    @AfterEach
    void tearDown() {
        if (!testSubscriber.isDisposed()) testSubscriber.dispose();
    }

    @Nested
    @DisplayName("Given get payment method succeeds")
    class GetPaymentSucceeds {

        @Nested
        @DisplayName("When payment methods is not empty")
        class PaymentMethodNotEmpty {

            @Test
            @DisplayName("Then it should return payment methods list")
            public void shouldReturnList() {
                final List<PaymentMethod> paymentMethods = new ArrayList<>();
                paymentMethods.add(DomainTestData.TEST_PAYMENT_METHOD);

                Mockito.when(paymentRepository.getPaymentMethods()).thenReturn(Single.just(paymentMethods));

                underTest.apply().subscribe(testSubscriber);

                Mockito.verify(paymentRepository, Mockito.times(1)).getPaymentMethods();
                Mockito.verifyNoMoreInteractions(paymentRepository);

                testSubscriber.assertComplete();
                testSubscriber.assertValue(paymentMethods);
            }
        }

        @Nested
        @DisplayName("When payment methods is empty")
        class PaymentMethodIsEmpty {

            @Test
            @DisplayName("Then it should return empty list")
            public void shouldReturnEmptyList() {
                final List<PaymentMethod> emptyMethods = new ArrayList<>(0);


                Mockito.when(paymentRepository.getPaymentMethods()).thenReturn(Single.just(emptyMethods));

                underTest.apply().subscribe(testSubscriber);

                Mockito.verify(paymentRepository, Mockito.times(1)).getPaymentMethods();
                Mockito.verifyNoMoreInteractions(paymentRepository);

                testSubscriber.assertComplete();
                testSubscriber.assertValue(emptyMethods);
            }
        }
    }

    @Nested
    @DisplayName("Given get payment method fails")
    class GetPaymentFails {

        @Test
        @DisplayName("Then it should emit error")
        public void shouldReturnEmptyList() {

            Mockito.when(paymentRepository.getPaymentMethods()).thenReturn(Single.error(RuntimeException::new));

            underTest.apply().subscribe(testSubscriber);

            Mockito.verify(paymentRepository, Mockito.times(1)).getPaymentMethods();
            Mockito.verifyNoMoreInteractions(paymentRepository);

            testSubscriber.assertNotComplete();
            testSubscriber.assertError(RuntimeException.class);
        }

    }

}