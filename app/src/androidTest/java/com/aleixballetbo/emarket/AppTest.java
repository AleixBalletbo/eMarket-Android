package com.aleixballetbo.emarket;


import com.aleixballetbo.emarket.AddProduct.AddProductView;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailView;
import com.aleixballetbo.emarket.ProductList.ProductListView;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;

public class AppTest extends App {

    private ViewPresenterModule viewPresenterModule;

    @Override
    public ViewPresenterModule getViewPresenterModule (ProductListView view) {
        if (viewPresenterModule == null) {
            return super.getViewPresenterModule(view);
        }
        return viewPresenterModule;
    }

    @Override
    public ViewPresenterModule getViewPresenterModule (ProductDetailView view) {
        if (viewPresenterModule == null) {
            return super.getViewPresenterModule(view);
        }
        return viewPresenterModule;
    }

    @Override
    public ViewPresenterModule getViewPresenterModule (AddProductView view) {
        if (viewPresenterModule == null) {
            return super.getViewPresenterModule(view);
        }
        return viewPresenterModule;
    }

    public void setViewPresenterModule (ViewPresenterModule viewPresenterModule) {
        this.viewPresenterModule = viewPresenterModule;
    }
}
