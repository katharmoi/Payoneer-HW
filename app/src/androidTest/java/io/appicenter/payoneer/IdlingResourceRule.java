package io.appicenter.payoneer;

import androidx.test.espresso.IdlingRegistry;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.appicenter.payoneer.di.DaggerAppComponent;
import okhttp3.OkHttpClient;

public class IdlingResourceRule implements TestRule {

    OkHttpClient client = DaggerAppComponent.builder()
            .application(App.instance)
            .build()
            .getOkHttpClient();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                OkHttp3IdlingResource resource = OkHttp3IdlingResource.create("okhttp", client);
                IdlingRegistry.getInstance().register(resource);
                base.evaluate();
                IdlingRegistry.getInstance().unregister(resource);
            }
        };
    }
}
