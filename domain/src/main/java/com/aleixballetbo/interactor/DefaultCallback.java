package com.aleixballetbo.interactor;

import com.aleixballetbo.exception.ErrorBundle;

public interface DefaultCallback<T> {

    void onError(ErrorBundle errorBundle);

    void onSuccess(T returnParam);
}
