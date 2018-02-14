package com.aleixballetbo.emarket.ProductDetail;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.GetProductDetailInteractor;

public class ProductDetailPresenter {
    private final ProductDetailView view;
    private final GetProductDetailInteractor getProductDetailInteractor;

    public ProductDetailPresenter(ProductDetailView view, GetProductDetailInteractor getProductDetailInteractor) {
        this.view = view;
        this.getProductDetailInteractor = getProductDetailInteractor;
    }

    public void onStart () {
        getProductDetailInteractor.execute(new GetProductDetailInteractor.GetProductDetailCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.showError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Product returnParam) {
                view.showData(returnParam);
            }
        });
    }
}
