package com.aleixballetbo.emarket;


import com.aleixballetbo.emarket.ProductDetail.ProductDetailPresenter;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailView;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.DefaultCallback;
import com.aleixballetbo.interactor.GetProductDetailInteractor;

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

public class ProductDetailPresenterTest {
    @Mock
    private ProductDetailView productDetailView;
    @Mock
    private GetProductDetailInteractor getProductDetailInteractor;
    @InjectMocks
    private ProductDetailPresenter productDetailPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void showProduct () {
        final Product product = new Product("0", "iPhone X", 1159, "3 mesos d'antiguitat", "user@email.com");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DefaultCallback<Product>) invocation.getArgument(1)).onSuccess(product);
                return null;
            }
        }).when(getProductDetailInteractor).execute(any(String.class), any(DefaultCallback.class));

        productDetailPresenter.onStart("0");

        verify(productDetailView).showData(product);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void showProductFailure () {
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
        }).when(getProductDetailInteractor).execute(any(String.class), any(DefaultCallback.class));

        productDetailPresenter.onStart("-1");

        verify(productDetailView).showError(errorMessage);
    }

}
