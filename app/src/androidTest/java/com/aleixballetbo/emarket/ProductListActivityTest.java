package com.aleixballetbo.emarket;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.aleixballetbo.emarket.ProductList.ProductListActivity;
import com.aleixballetbo.emarket.ProductList.ProductListPresenter;
import com.aleixballetbo.emarket.ProductList.ProductListView;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.interactor.GetProductsInteractor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
public class ProductListActivityTest {

    @Mock
    ViewPresenterModule module;

    @Mock
    ProductListPresenter presenter;

    @Rule
    public ActivityTestRule<ProductListActivity> activity = new ActivityTestRule<>(ProductListActivity.class, false, false);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(module.providesMainView()).thenReturn(mock(ProductListView.class));
        when(module.provideProductListPresenter(any(ProductListView.class), any(GetProductsInteractor.class))).thenReturn(presenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest app = (AppTest) instrumentation.getTargetContext().getApplicationContext();

        app.setViewPresenterModule(module);
    }

    @Test
    public void showProductList () {
        activity.launchActivity(new Intent());
        activity.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<Product> productList = new ArrayList<>();
                productList.add(new Product("0", "Product Test", 1000, "3 mesos d'antiguitat", "user@email.com"));
                activity.getActivity().showData(productList);
            }
        });
        onView(withId(R.id.productName)).check(matches(withText("Product Test")));
        onView(withId(R.id.productPrice)).check(matches(withText("1000.0€")));
    }


    @Test
    public void showProductListFailure () {
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
