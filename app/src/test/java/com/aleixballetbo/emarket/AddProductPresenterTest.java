package com.aleixballetbo.emarket;


import com.aleixballetbo.emarket.AddProduct.AddProductPresenter;
import com.aleixballetbo.emarket.AddProduct.AddProductView;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.AddProductInteractor;
import com.aleixballetbo.interactor.DefaultCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class AddProductPresenterTest {
    @Mock
    private AddProductView addProductView;
    @Mock
    private AddProductInteractor addProductInteractor;
    @InjectMocks
    private AddProductPresenter addProductPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void showProduct () {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DefaultCallback<Product>) invocation.getArgument(1)).onSuccess(null);
                return null;
            }
        }).when(addProductInteractor).execute(any(Product.class), any(DefaultCallback.class));

        addProductPresenter.addProduct("nom", "100", "descripció", "user@email.com");

        verify(addProductView).onSuccess();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void showProductError () {
        final String errorMessage = "ERROR";
        final ErrorBundle errorBundle = new ErrorBundle() {
            @Override
            public Exception getException() {
                return null;
            }

            @Override
            public String getErrorMessage() {
                return errorMessage;
            }
        };

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DefaultCallback<Product>) invocation.getArgument(1)).onError(errorBundle);
                return null;
            }
        }).when(addProductInteractor).execute(any(Product.class), any(DefaultCallback.class));

        addProductPresenter.addProduct("nom", "100", "descripció", "user@email.com");

        verify(addProductView).showError(errorMessage);
    }

    @Test
    public void showProductBlankName () {
        addProductPresenter.addProduct("", "100", "descripció", "user@email.com");
        verify(addProductView).showErrorProductName("Cal introduir un nom pel producte");
    }

    @Test
    public void showProductBlankPrice () {
        addProductPresenter.addProduct("nom", "", "descripció", "user@email.com");
        verify(addProductView).showErrorProductPrice("Cal introduir un preu pel producte");
    }

    @Test
    public void showProductBlankDescription () {
        addProductPresenter.addProduct("nom", "100", "", "user@email.com");
        verify(addProductView).showErrorProductDescription("Cal introduir una descripció pel producte");
    }

    @Test
    public void showProductBlankOwner () {
        addProductPresenter.addProduct("nom", "100", "descripció", "");
        verify(addProductView).showErrorProductOwner("Cal introduir un correu de contacte");
    }
}
