package com.aleixballetbo.emarket.ProductDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;
import com.aleixballetbo.entities.Product;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

    public static final String EXTRA_ID = "id";

    @Inject
    ProductDetailPresenter presenter;

    @Inject
    @ForActivity
    Context context;

    public static void actionOpenDetails(Context context, String id, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        if (options != null) {
            context.startActivity(intent, options.toBundle());
        }
        else {
            context.startActivity(intent);
        }
    }

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
        String id = getIntent().getStringExtra(EXTRA_ID);
        presenter.onStart(id);
    }

    @Override
    public void showData(Product products) {

    }

    @Override
    public void showError(String error) {

    }
}
