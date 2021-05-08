package io.appicenter.payoneer.home;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.appicenter.payoneer.IdlingResourceRule;

/**
 * End-to-End tests
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {

    @Rule
    public IdlingResourceRule rule = new IdlingResourceRule();

    @Test
    public void addProductsToCartAndRecreateActivity_cartIsRepopulatedOnResumed() {
        ActivityScenario.launch(HomeActivity.class);
    }


}