package io.appicenter.payoneer.home;

import com.google.common.truth.Truth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.appicenter.domain.interactor.system.ObserveNetworkUseCase;
import io.appicenter.payoneer.InstantExecutorExtension;
import io.appicenter.payoneer.TestObserver;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

@ExtendWith(value = {InstantExecutorExtension.class})
class HomeViewModelTest {

    private HomeViewModel underTest;
    private final TestScheduler scheduler = new TestScheduler();

    private ObserveNetworkUseCase observeNetworkUseCase;

    @BeforeEach
    void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(ioSh -> scheduler);
        observeNetworkUseCase = Mockito.mock(ObserveNetworkUseCase.class);
        underTest = new HomeViewModel(observeNetworkUseCase, scheduler);
    }

    @AfterEach
    void tearDown() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

    @Nested
    @DisplayName("Given observeNetwork called")
    class ObserveNetworkCalled {

        @Nested
        @DisplayName("When there is no network")
        class NoNetwork {

            @BeforeEach
            void setUp() {
                Mockito.when(observeNetworkUseCase.apply()).thenReturn(Observable.just(false));
            }

            @Test
            @DisplayName("Then it should emit false")
            public void shouldReturnFalse() {


                TestObserver<Boolean> networkStateLiveData = new TestObserver<>();
                underTest.networkState.observeForever(networkStateLiveData);

                underTest.observeNetwork();

                scheduler.triggerActions();

                Mockito.verify(observeNetworkUseCase, Mockito.times(1)).apply();
                Mockito.verifyNoMoreInteractions(observeNetworkUseCase);

                Truth.assert_()
                        .that(networkStateLiveData.observedValues.get(0))
                        .isEqualTo(false);

            }

        }

        @Nested
        @DisplayName("When network OK")
        class NetworkExists {

            @BeforeEach
            void setUp() {
                Mockito.when(observeNetworkUseCase.apply()).thenReturn(Observable.just(true));
            }

            @Test
            @DisplayName("Then it should emit true")
            public void shouldReturnTrue() {


                TestObserver<Boolean> networkStateLiveData = new TestObserver<>();
                underTest.networkState.observeForever(networkStateLiveData);

                underTest.observeNetwork();

                scheduler.triggerActions();

                Mockito.verify(observeNetworkUseCase, Mockito.times(1)).apply();
                Mockito.verifyNoMoreInteractions(observeNetworkUseCase);

                Truth.assert_()
                        .that(networkStateLiveData.observedValues.get(0))
                        .isEqualTo(true);

            }

        }

    }
}