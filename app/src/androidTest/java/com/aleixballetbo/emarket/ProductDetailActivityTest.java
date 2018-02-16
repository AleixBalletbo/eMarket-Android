package com.aleixballetbo.emarket;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.aleixballetbo.emarket.ProductDetail.ProductDetailActivity;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailPresenter;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailView;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.interactor.GetProductDetailInteractor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ProductDetailActivityTest {

    @Mock
    ViewPresenterModule module;

    @Mock
    ProductDetailPresenter presenter;

    @Rule
    public ActivityTestRule<ProductDetailActivity> activity = new ActivityTestRule<>(ProductDetailActivity.class, false, false);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(module.providesDetailView()).thenReturn(mock(ProductDetailView.class));
        when(module.provideProductDetailPresenter(any(ProductDetailView.class), any(GetProductDetailInteractor.class))).thenReturn(presenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest app = (AppTest) instrumentation.getTargetContext().getApplicationContext();

        app.setViewPresenterModule(module);
    }

    @Test
    public void showProductDetail () {
        activity.launchActivity(new Intent());
        activity.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Product product = new Product("0", "Product Test", 1000, "3 mesos d'antiguitat", "user@email.com");
                activity.getActivity().showData(product);
            }
        });
        onView(withId(R.id.nameText)).check(matches(withText("Product Test")));
        onView(withId(R.id.priceText)).check(matches(withText("1000€")));
        onView(withId(R.id.ownerText)).check(matches(withText("user@email.com")));
        onView(withId(R.id.descriptionText)).check(matches(withText("3 mesos d'antiguitat")));
    }


    @Test
    public void showProductDetailFailure () {
        activity.launchActivity(new Intent());
        activity.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.getActivity().showError("ERROR");
            }
        });
        onView(withText("ERROR")).inRoot(withDecorView(not(is(activity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
