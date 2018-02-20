package com.aleixballetbo.emarket.dependencyinjection.application;


import com.aleixballetbo.emarket.AddProduct.AddProductPresenter;
import com.aleixballetbo.emarket.AddProduct.AddProductView;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailPresenter;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailView;
import com.aleixballetbo.emarket.ProductList.ProductListPresenter;
import com.aleixballetbo.emarket.ProductList.ProductListView;
import com.aleixballetbo.interactor.AddProductInteractor;
import com.aleixballetbo.interactor.GetProductDetailInteractor;
import com.aleixballetbo.interactor.GetProductsInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewPresenterModule {

    private ProductListView listView;
    private ProductDetailView detailView;
    private AddProductView addProductView;

    public ViewPresenterModule(ProductListView listView) {
        this.listView = listView;
    }

    public ViewPresenterModule(ProductDetailView detailView) {
        this.detailView = detailView;
    }

    public ViewPresenterModule(AddProductView addProductView) {
        this.addProductView = addProductView;
    }

    @Provides
    public ProductListView providesMainView(){
        return listView;
    }

    @Provides
    public ProductDetailView providesDetailView() {
        return detailView;
    }

    @Provides
    public AddProductView providesAddProductView() {
        return addProductView;
    }

    @Provides
    public ProductListPresenter provideProductListPresenter (ProductListView view, GetProductsInteractor interactor) {
        return new ProductListPresenter(view, interactor);
    }

    @Provides
    public ProductDetailPresenter provideProductDetailPresenter (ProductDetailView detailView, GetProductDetailInteractor interactor) {
        return new ProductDetailPresenter(detailView, interactor);
    }

    @Provides
    public AddProductPresenter provideAddProductPresenter (AddProductView addProductView, AddProductInteractor interactor) {
        return new AddProductPresenter(addProductView, interactor);
    }
}
