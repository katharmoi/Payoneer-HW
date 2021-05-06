package io.appicenter.payoneer.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Response<T> {

    public final Status status;
    public final T data;
    public final Throwable error;

    private Response(final Status status, final T data, final Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> loading() {
        return new Response<>(Status.LOADING, null, null);
    }

    public static <T> Response<T> success(T data) {
        Objects.requireNonNull(data);
        return new Response<>(Status.SUCCESS, data, null);
    }

    public static <T> Response<T> error(Throwable error) {
        return new Response<>(Status.ERROR, null, error);
    }


    @Override
    public @NotNull String toString() {
        return "Response{" +
                "status=" + status +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}
