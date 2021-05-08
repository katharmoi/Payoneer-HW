package io.appicenter.payoneer.payment;

import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import java.util.ArrayList;
import io.appicenter.payoneer.R;
import io.appicenter.payoneer.data.FakePaymentRepository;
import io.appicenter.payoneer.di.TestApp;
import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


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

        FakePaymentRepository.result = new ArrayList<>();

        launchInContainer(PaymentMethodsFragment.class,null,R.style.Theme_AppTheme);

        onView(withId(R.id.payment_methods_empty_view)).check(matches(isDisplayed()));
    }
}
