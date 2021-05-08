package io.appicenter.payoneer.utils;

import android.content.Context;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.appicenter.payoneer.R;
import retrofit2.HttpException;

public class ErrorParser {

    private final Context context;

    @Inject
    public ErrorParser(Context context) {
        this.context = context;
    }

    public String parse(Throwable error) {
        String errorMessage;
        if (error.getClass() == HttpException.class) {
            switch (((HttpException) error).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    errorMessage = context.getString(R.string.error_unauthorised_user);
                    break;
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    errorMessage = context.getString(R.string.error_forbidden);
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    errorMessage = context.getString(R.string.error_internal_server);
                    break;
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    errorMessage = context.getString(R.string.error_bad_request);
                    break;
                case HttpsURLConnection.HTTP_NOT_FOUND:
                    errorMessage = context.getString(R.string.error_not_found);
                    break;
                default:
                    errorMessage = context.getString(R.string.error_generic);
            }
        } else if (error.getClass() == NoNetworkException.class) {
            errorMessage = context.getString(R.string.error_no_network);
        } else {
            if (error.getMessage() != null) {
                errorMessage = error.getMessage();
            } else {
                errorMessage = context.getString(R.string.error_generic);
            }
        }

        return errorMessage;

    }
}
