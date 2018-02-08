package com.aleixballetbo.emarket.dependencyinjection.activity;

import com.aleixballetbo.emarket.dependencyinjection.application.ViewModule;
import com.aleixballetbo.emarket.dependencyinjection.scope.PerActivity;
import com.aleixballetbo.emarket.ProductList.ProductListActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class})
public interface ActivityComponent {

    void inject(ProductListActivity activity);
}
