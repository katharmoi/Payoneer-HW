package io.appicenter.domain.interactor.type;


import io.reactivex.Observable;

public interface ObservableUseCase<T> {

    Observable<T> apply();
}
