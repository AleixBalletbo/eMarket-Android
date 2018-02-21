package com.aleixballetbo.emarket.AddProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @Inject
    AddProductPresenter presenter;

    @Inject
    @ForActivity
    Context context;

    @BindView(R.id.nameText)
    EditText productName;
    @BindView(R.id.priceText)
    EditText productPrice;
    @BindView(R.id.ownerText)
    EditText productOwner;
    @BindView(R.id.descriptionText)
    EditText productDescription;


    public static void actionOpenAddProduct(Context context, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, AddProductActivity.class);
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
        setContentView(R.layout.activity_add_product);

        ButterKnife.bind(this);

        App app = (App) getApplication();
        app.getComponent()
                .plus(new ActivityModule(this),
                        app.getViewPresenterModule(this))
                .inject(this);
    }

    @OnClick(R.id.addProductButton)
    public void addProduct () {
        presenter.addProduct(productName.getText().toString(), productPrice.getText().toString(), productDescription.getText().toString(), productOwner.getText().toString());
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void showError(String error) {
        Toast t = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void showErrorProductName(String error) {
        productName.setError(error);
    }

    @Override
    public void showErrorProductPrice(String error) {
        productPrice.setError(error);
    }

    @Override
    public void showErrorProductOwner(String error) {
        productOwner.setError(error);
    }

    @Override
    public void showErrorProductDescription(String error) {
        productDescription.setError(error);
    }
}
