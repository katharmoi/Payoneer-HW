package io.appicenter.domain.interactor.payment.type;


import io.reactivex.Single;

public interface SingleUseCase<T> {

    Single<T> apply();
}
