package io.appicenter.payoneer.payment;

import com.google.common.truth.Truth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.appicenter.domain.interactor.payment.GetPaymentMethodsUseCase;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.payoneer.InstantExecutorExtension;
import io.appicenter.payoneer.TestData;
import io.appicenter.payoneer.TestObserver;
import io.appicenter.payoneer.utils.Response;
import io.appicenter.payoneer.utils.Status;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;


@ExtendWith(value = InstantExecutorExtension.class)
class PaymentViewModelTest {

    private PaymentViewModel underTest;
    private final TestScheduler scheduler = new TestScheduler();
    private GetPaymentMethodsUseCase getPaymentMethodsUseCase;

    @BeforeEach
    void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(ioSh -> scheduler);
        getPaymentMethodsUseCase = Mockito.mock(GetPaymentMethodsUseCase.class);
        underTest = new PaymentViewModel(getPaymentMethodsUseCase, scheduler, scheduler);
    }

    @AfterEach
    void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

    @Nested
    @DisplayName("Given getPaymentMethods called")
    class GetPaymentMethodsNetworkCalled {

        @Nested
        @DisplayName("When there is error")
        class ErrorState {

            @BeforeEach
            void setUp() {
                Mockito.when(getPaymentMethodsUseCase.apply()).thenReturn(Single.error(new IOException()));
            }

            @Test
            @DisplayName("Then it should emit error")
            public void shouldReturnError() {


                TestObserver<Response<List<PaymentMethod>>> paymentMethodsObserver = new TestObserver<>();
                underTest.paymentMethods.observeForever(paymentMethodsObserver);

                underTest.getPaymentMethods();

                scheduler.triggerActions();

                Mockito.verify(getPaymentMethodsUseCase, Mockito.times(1)).apply();
                Mockito.verifyNoMoreInteractions(getPaymentMethodsUseCase);

                Truth.assert_()
                        .that(paymentMethodsObserver.observedValues.get(0).status)
                        .isEqualTo(Status.LOADING);

                Truth.assert_()
                        .that(paymentMethodsObserver.observedValues.get(1).error.getClass())
                        .isEqualTo(IOException.class);

            }

        }

        @Nested
        @DisplayName("When there is no error")
        class SuccessState {

            List<PaymentMethod> testList = new ArrayList<>();

            @BeforeEach
            void setUp() {
                testList.add(TestData.TEST_PAYMENT_METHOD);
                Mockito.when(getPaymentMethodsUseCase.apply()).thenReturn(Single.just(testList));
            }

            @Test
            @DisplayName("Then it should emit payment methods list")
            public void shouldReturnList() {


                TestObserver<Response<List<PaymentMethod>>> paymentMethodsObserver = new TestObserver<>();
                underTest.paymentMethods.observeForever(paymentMethodsObserver);

                underTest.getPaymentMethods();

                scheduler.triggerActions();

                Mockito.verify(getPaymentMethodsUseCase, Mockito.times(1)).apply();
                Mockito.verifyNoMoreInteractions(getPaymentMethodsUseCase);

                Truth.assert_()
                        .that(paymentMethodsObserver.observedValues.get(0).status)
                        .isEqualTo(Status.LOADING);

                Truth.assert_()
                        .that(paymentMethodsObserver.observedValues.get(1).data)
                        .isEqualTo(testList);

            }

        }
    }


}