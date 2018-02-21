package com.aleixballetbo.emarket.ProductList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;
import com.aleixballetbo.entities.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity implements ProductListView {

    @Inject
    ProductListPresenter presenter;

    @Inject
    @ForActivity
    Context context;

    @Inject
    ProductAdapter productAdapter;

    @BindView(R.id.productList)
    RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ButterKnife.bind(this);

        App app = (App) getApplication();
        app.getComponent()
                .plus(new ActivityModule(this),
                        app.getViewPresenterModule(this))
                .inject(this);

        setUpRecyclerView();
        presenter.onStart();
    }

    @Override
    protected void onResume () {
        super.onResume();
        presenter.onStart();
    }

    @OnClick(R.id.addProductButton)
    public void addProduct () {
        presenter.openAddProduct(context);
    }

    private void setUpRecyclerView () {
        productList.setLayoutManager(new LinearLayoutManager(this));
        productList.setAdapter(productAdapter);
    }

    @Override
    public void showData(List<Product> products) {
        productAdapter.setData(products);
    }

    @Override
    public void showError(String error) {
        Toast t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
        t.show();
    }
}
