package com.aleixballetbo.emarket;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.aleixballetbo.emarket.AddProduct.AddProductActivity;
import com.aleixballetbo.emarket.AddProduct.AddProductPresenter;
import com.aleixballetbo.emarket.AddProduct.AddProductView;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;
import com.aleixballetbo.interactor.AddProductInteractor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class AddProductActivityTest {

    @Mock
    ViewPresenterModule module;

    @Mock
    AddProductPresenter presenter;

    @Rule
    public ActivityTestRule<AddProductActivity> activity = new ActivityTestRule<>(AddProductActivity.class, false, false);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(module.providesAddProductView()).thenReturn(mock(AddProductView.class));
        when(module.provideAddProductPresenter(any(AddProductView.class), any(AddProductInteractor.class))).thenReturn(presenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest app = (AppTest) instrumentation.getTargetContext().getApplicationContext();

        app.setViewPresenterModule(module);
    }

    @Test
    public void addProduct() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().onSuccess();
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.nameText)).perform(typeText("Product Test"));
        onView(withId(R.id.priceText)).perform(typeText("1000"));
        onView(withId(R.id.ownerText)).perform(typeText("user@email.com"));
        onView(withId(R.id.descriptionText)).perform(typeText("3 mesos d'antiguitat"), closeSoftKeyboard());

        onView(withId(R.id.addProductButton)).perform(click());

        assertTrue(activity.getActivity().isFinishing());
    }

    @Test
    public void addProductError() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().showError("ERROR");
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.nameText)).perform(typeText("Product Test"));
        onView(withId(R.id.priceText)).perform(typeText("1000"));
        onView(withId(R.id.ownerText)).perform(typeText("user@email.com"));
        onView(withId(R.id.descriptionText)).perform(typeText("3 mesos d'antiguitat"), closeSoftKeyboard());

        onView(withId(R.id.addProductButton)).perform(click());

        onView(withText("ERROR")).inRoot(withDecorView(not(is(activity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void addProductEmptyName() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().showErrorProductName("Cal introduir un nom pel producte");
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.nameText)).perform(clearText(),closeSoftKeyboard());
        onView(withId(R.id.addProductButton)).perform(click());
        onView(withId(R.id.nameText)).check(matches(hasErrorText("Cal introduir un nom pel producte")));
    }

    @Test
    public void addProductEmtpyPrice() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().showErrorProductPrice("Cal introduir un preu pel producte");
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.priceText)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.addProductButton)).perform(click());
        onView(withId(R.id.priceText)).check(matches(hasErrorText("Cal introduir un preu pel producte")));
    }

    @Test
    public void addProductEmtpyOwner() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().showErrorProductOwner("Cal introduir un correu de contacte");
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.ownerText)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.addProductButton)).perform(click());
        onView(withId(R.id.ownerText)).check(matches(hasErrorText("Cal introduir un correu de contacte")));
    }

    @Test
    public void addProductEmtpyDescription() {
        activity.launchActivity(new Intent());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                activity.getActivity().showErrorProductDescription("Cal introduir una descripció pel producte");
                return null;
            }
        }).when(presenter).addProduct(anyString(), anyString(), anyString(), anyString());

        onView(withId(R.id.descriptionText)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.addProductButton)).perform(click());
        onView(withId(R.id.descriptionText)).check(matches(hasErrorText("Cal introduir una descripció pel producte")));
    }
}
