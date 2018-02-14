package com.aleixballetbo.emarket.ProductDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;
import com.aleixballetbo.entities.Product;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

    @Inject
    ProductDetailPresenter presenter;

    @Inject
    @ForActivity
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ButterKnife.bind(this);

        App app = (App) getApplication();
        app.getComponent()
                .plus(new ActivityModule(this),
                        app.getViewPresenterModule(this))
                .inject(this);

        presenter.onStart();
    }

    @Override
    public void showData(Product products) {

    }

    @Override
    public void showError(String error) {

    }
}
