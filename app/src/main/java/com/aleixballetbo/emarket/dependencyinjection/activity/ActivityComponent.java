package com.aleixballetbo.emarket.dependencyinjection.activity;

import com.aleixballetbo.emarket.ProductDetail.ProductDetailActivity;
import com.aleixballetbo.emarket.ProductList.ProductListActivity;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;
import com.aleixballetbo.emarket.dependencyinjection.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewPresenterModule.class})
public interface ActivityComponent {

    void inject(ProductListActivity activity);

    void inject(ProductDetailActivity activity);
}
