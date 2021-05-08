package io.appicenter.payoneer.home;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.appicenter.payoneer.IdlingResourceRule;
import io.appicenter.payoneer.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * End-to-End tests
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {

    @Rule
    public IdlingResourceRule rule = new IdlingResourceRule();


    @Test
    public void paymentMethods() {
        ActivityScenario.launch(HomeActivity.class);

        onView(withId(R.id.payment_methods_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }


}