package io.appicenter.payoneer.payment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.appicenter.domain.interactor.payment.GetPaymentMethodsUseCase;
import io.appicenter.domain.model.PaymentMethod;
import io.appicenter.payoneer.base.BaseViewModel;
import io.appicenter.payoneer.utils.Response;
import io.reactivex.Scheduler;
import timber.log.Timber;

public class PaymentViewModel extends BaseViewModel {

    private final GetPaymentMethodsUseCase getPaymentMethodsUseCase;
    private final Scheduler bgScheduler;
    private final Scheduler mainScheduler;

    public PaymentViewModel(
            GetPaymentMethodsUseCase getPaymentMethodsUseCase,
            Scheduler bgScheduler,
            Scheduler mainScheduler) {
        this.getPaymentMethodsUseCase = getPaymentMethodsUseCase;
        this.bgScheduler = bgScheduler;
        this.mainScheduler = mainScheduler;
    }

    private final MutableLiveData<Response<List<PaymentMethod>>> _paymentMethods = new MutableLiveData<>();
    public final LiveData<Response<List<PaymentMethod>>> paymentMethods = _paymentMethods;


    public void getPaymentMethods() {
        disposables.add(
                getPaymentMethodsUseCase.apply()
                        .doOnSubscribe(disposable -> _paymentMethods.postValue(Response.loading()))
                        .subscribeOn(bgScheduler)
                        .observeOn(mainScheduler)
                        .subscribe(
                                data -> _paymentMethods.setValue(Response.success(data)),
                                error -> {
                                    _paymentMethods.setValue(Response.error(error));
                                    Timber.e(error);
                                }

                        )

        );
    }


}