package com.aleixballetbo.emarket.AddProduct;

import com.aleixballetbo.entities.Product;

public interface AddProductView {

    void onSuccess(Product products);

    void showError(String error);
}
