package com.aleixballetbo.emarket.dependencyinjection.application;


import com.aleixballetbo.emarket.ProductList.ProductListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private ProductListView view;

    public ViewModule(ProductListView view) {
        this.view = view;
    }

    @Provides
    ProductListView providesMainView(){
        return view;
    }
}
