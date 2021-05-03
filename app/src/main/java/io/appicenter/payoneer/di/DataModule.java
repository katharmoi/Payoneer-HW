package io.appicenter.payoneer.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.appicenter.data.BuildConfig;
import io.appicenter.data.payment.service.PaymentApi;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Providers for repository, service and Retrofit objects
 */
@Module
public class DataModule {

    private static final String BASE_URL =
            "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/";

    private static final String OKHTTP_NETWORK_INTERCEPTOR = "network_check";

    @Singleton
    @Provides
    public PaymentApi provideApi(Retrofit retrofit) {
        return retrofit.create(PaymentApi.class);
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(
            Cache cache,
            HttpLoggingInterceptor loggingInterceptor,
            @Named(OKHTTP_NETWORK_INTERCEPTOR) Interceptor networkInterceptor
    ) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level;

        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY;
        else level = HttpLoggingInterceptor.Level.NONE;

        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverter() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(
            GsonConverterFactory gsonConverterFactory,
            RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
            OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build();
    }

}
