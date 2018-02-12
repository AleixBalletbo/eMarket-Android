package com.aleixballetbo.emarket.dependencyinjection.application;


import com.aleixballetbo.emarket.ProductList.ProductListPresenter;
import com.aleixballetbo.emarket.ProductList.ProductListView;
import com.aleixballetbo.interactor.GetProductsInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewPresenterModule {

    private ProductListView view;

    public ViewPresenterModule(ProductListView view) {
        this.view = view;
    }

    @Provides
    ProductListView providesMainView(){
        return view;
    }

    @Provides
    public ProductListPresenter provideProductListPresenter (ProductListView view, GetProductsInteractor interactor) {
        return new ProductListPresenter(view, interactor);
    }
}
