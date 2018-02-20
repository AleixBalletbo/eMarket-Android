package com.aleixballetbo.emarket;

import android.app.Application;

import com.aleixballetbo.emarket.AddProduct.AddProductView;
import com.aleixballetbo.emarket.ProductDetail.ProductDetailView;
import com.aleixballetbo.emarket.ProductList.ProductListView;
import com.aleixballetbo.emarket.dependencyinjection.application.ApplicationComponent;
import com.aleixballetbo.emarket.dependencyinjection.application.ApplicationModule;
import com.aleixballetbo.emarket.dependencyinjection.application.DaggerApplicationComponent;
import com.aleixballetbo.emarket.dependencyinjection.application.ViewPresenterModule;

public class App extends Application {

    ApplicationComponent component = null;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ViewPresenterModule getViewPresenterModule (ProductListView view) {
        return new ViewPresenterModule(view);
    }

    public ViewPresenterModule getViewPresenterModule (ProductDetailView view) {
        return new ViewPresenterModule(view);
    }

    public ViewPresenterModule getViewPresenterModule (AddProductView view) {
        return new ViewPresenterModule(view);
    }
}
