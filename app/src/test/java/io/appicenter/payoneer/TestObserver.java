package io.appicenter.payoneer;

import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

public class TestObserver<T> implements Observer<T> {

    public List<T> observedValues = new ArrayList<>();

    @Override
    public void onChanged(T t) {
        observedValues.add(t);
    }
}
