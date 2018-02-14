package com.aleixballetbo.emarket.ProductDetail;

import com.aleixballetbo.entities.Product;

public interface ProductDetailView {

    void showData(Product products);

    void showError(String error);
}
