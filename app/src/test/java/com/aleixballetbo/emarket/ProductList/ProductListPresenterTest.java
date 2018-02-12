package com.aleixballetbo.emarket.ProductList;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.DefaultCallback;
import com.aleixballetbo.interactor.GetProductsInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class ProductListPresenterTest {
    @Mock
    private ProductListView productListView;
    @Mock
    private GetProductsInteractor getProductsInteractor;
    @InjectMocks
    private ProductListPresenter productListPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadProducts () {
        final List<Product> productList = new ArrayList<>();
        productList.add(new Product("0", "iPhone X", 1159, "3 mesos d'antiguitat", "user@email.com"));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DefaultCallback<List<Product>>) invocation.getArgument(0)).onSuccess(productList);
                return null;
            }
        }).when(getProductsInteractor).execute(any(DefaultCallback.class));

        productListPresenter.onStart();

        verify(productListView).showData(productList);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadProductsFailure () {
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
                ((DefaultCallback<List<Product>>) invocation.getArgument(0)).onError(errorBundle);
                return null;
            }
        }).when(getProductsInteractor).execute(any(DefaultCallback.class));

        productListPresenter.onStart();

        verify(productListView).showError(errorMessage);
    }

}
