package io.appicenter.data.payment.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appicenter.data.payment.service.model.ApiPaymentMethod;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.truth.Truth.assertThat;

class PaymentServiceImplTest {
    private PaymentService underTest;
    private final MockWebServer mockWebServer = new MockWebServer();
    private final TestObserver<List<ApiPaymentMethod>> testSubscriber = new TestObserver<>();

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1000, TimeUnit.MILLISECONDS)
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .writeTimeout(1000, TimeUnit.MILLISECONDS)
            .build();

    private final Gson gson = new GsonBuilder().setLenient().create();

    private final PaymentApi api = new Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PaymentApi.class);

    @BeforeEach
    void setUp() {
        underTest = new PaymentServiceImpl(api);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
        if (!testSubscriber.isDisposed()) testSubscriber.dispose();
    }

    private String readTestFiles(final String filename) throws IOException {
        FileReader fr = new FileReader("src/test/resources/" + filename);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = fr.read()) != -1) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    @Nested
    @DisplayName("Given HTTP 200")
    class Http_OK {

        @Nested
        @DisplayName("When payment methods is not empty")
        class PaymentMethodNotEmpty {

            @Test
            @DisplayName("Then it should return payment methods list")
            public void shouldReturnList() throws IOException {

                MockResponse response = new MockResponse().setResponseCode(200).setBody(readTestFiles("discover_payment_methods_200.json"));
                mockWebServer.enqueue(response);

                underTest.getPaymentMethods().subscribe(testSubscriber);

                testSubscriber.assertComplete();
                testSubscriber.assertNoErrors();
                testSubscriber.assertValueCount(1);
                ApiPaymentMethod firstMethod = testSubscriber.values().get(0).get(0);
                assertThat(testSubscriber.values().get(0)).hasSize(17);
                assertThat(firstMethod.code).isEqualTo(ServiceTestData.CODE);
                assertThat(firstMethod.label).isEqualTo(ServiceTestData.LABEL);
                assertThat(firstMethod.method).isEqualTo(ServiceTestData.METHOD);
            }
        }

    }

    @Nested
    @DisplayName("Given HTTP 400")
    class Http_400 {


        @Test
        @DisplayName("Then it should emit HttpException")
        public void shouldReturnList() {

            MockResponse response = new MockResponse().setResponseCode(400);
            mockWebServer.enqueue(response);

            underTest.getPaymentMethods().subscribe(testSubscriber);

            testSubscriber.assertNotComplete();
            testSubscriber.assertError(HttpException.class);

        }
    }


}