package com.aleixballetbo.emarket.AddProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.R;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;
import com.aleixballetbo.entities.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @BindView(R.id.nameText)
    TextView productName;
    @BindView(R.id.priceText)
    TextView productPrice;
    @BindView(R.id.ownerText)
    TextView productOwner;
    @BindView(R.id.descriptionText)
    TextView productDescription;


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

    @Override
    public void onSuccess(Product products) {

    }

    @Override
    public void showError(String error) {

    }
}
