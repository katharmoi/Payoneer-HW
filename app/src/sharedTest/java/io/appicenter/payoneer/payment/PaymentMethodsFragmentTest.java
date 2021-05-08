package io.appicenter.payoneer.payment;

import android.os.Build;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;

import java.util.ArrayList;

import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;

import io.appicenter.payoneer.R;
import io.appicenter.payoneer.data.FakePaymentRepository;
import io.appicenter.payoneer.di.TestApp;
import io.appicenter.payoneer.utils.NoNetworkException;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.HttpException;
import retrofit2.Response;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Unit test for the payments screen
 */
@LooperMode(LooperMode.Mode.PAUSED)
@MediumTest
@Config(sdk = {Build.VERSION_CODES.P}, application = TestApp.class)
@RunWith(AndroidJUnit4.class)
public class PaymentMethodsFragmentTest {

    @Test
    public void displayEmptyView_whenRepositoryHasNoData() {

        //GIVEN - NoNetwork error is emitted
        FakePaymentRepository.methods = Single.error(NoNetworkException::new);

        //WHEN - on launch
        launchInContainer(PaymentMethodsFragment.class, null, R.style.Theme_AppTheme);

        //THEN - verify no network screen is displayed
        onView(withId(R.id.payment_methods_empty_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void displayMethods_whenRepositoryHasData() {

        //GIVEN - repository gets data
        FakePaymentRepository.methods = Single.just(new ArrayList<>());

        //WHEN - on launch
        launchInContainer(PaymentMethodsFragment.class, null, R.style.Theme_AppTheme);

        //THEN - verify recycler is displayed
        onView(withId(R.id.payment_methods_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void display404Snack_whenRepositoryReturns404() {

        HttpException notFoundException = new HttpException(Response.error(HttpsURLConnection.HTTP_NOT_FOUND, new ResponseBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        }));

        //GIVEN - 404  is emitted
        FakePaymentRepository.methods = Single.error(notFoundException);

        //WHEN - on launch
        launchInContainer(PaymentMethodsFragment.class, null, R.style.Theme_AppTheme);

        //THEN - verify 404 Snackbar is displayed
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.error_not_found)));

    }

    @Test
    public void display500Snack_whenRepositoryReturns500() {

        HttpException notFoundException = new HttpException(Response.error(HttpsURLConnection.HTTP_INTERNAL_ERROR, new ResponseBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        }));

        //GIVEN - 500  is emitted
        FakePaymentRepository.methods = Single.error(notFoundException);

        //WHEN - on launch
        launchInContainer(PaymentMethodsFragment.class, null, R.style.Theme_AppTheme);

        //THEN - verify 404 Snackbar is displayed
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.error_internal_server)));
    }

    @Test
    public void displayGenericSnack_whenRepositoryReturnsUnhandledError() {
        //GIVEN - Unhandled error  is emitted
        FakePaymentRepository.methods = Single.error(RuntimeException::new);

        //WHEN - on launch
        launchInContainer(PaymentMethodsFragment.class, null, R.style.Theme_AppTheme);

        //THEN - verify generic Snackbar is displayed
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.error_generic)));
    }

}
