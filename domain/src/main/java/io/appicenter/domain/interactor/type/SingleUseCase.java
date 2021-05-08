package io.appicenter.domain.interactor.type;


import io.reactivex.Single;

public interface SingleUseCase<T> {

    Single<T> apply();
}
