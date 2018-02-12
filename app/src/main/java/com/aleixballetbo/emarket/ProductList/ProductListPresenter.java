package com.aleixballetbo.emarket.ProductList;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.GetProductsInteractor;

import java.util.List;

import javax.inject.Inject;

public class ProductListPresenter {
    private final ProductListView view;
    private final GetProductsInteractor getProductsInteractor;

    @Inject
    public ProductListPresenter(ProductListView view, GetProductsInteractor getProductsInteractor) {
        this.view = view;
        this.getProductsInteractor = getProductsInteractor;
    }

    public void onStart () {
        getProductsInteractor.execute(new GetProductsInteractor.GetProductsCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(List<Product> returnParam) {
                view.showData(returnParam);
            }
        });
    }
}
