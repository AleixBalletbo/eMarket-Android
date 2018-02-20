package com.aleixballetbo.emarket.AddProduct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.entities.Product;

import butterknife.ButterKnife;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        ButterKnife.bind(this);

        App app = (App) getApplication();
        app.getComponent()
                .plus(new ActivityModule(this),
                        app.getViewPresenterModule(this))
                .inject(this);
    }

    @Override
    public void showData(Product products) {

    }

    @Override
    public void showError(String error) {

    }
}
