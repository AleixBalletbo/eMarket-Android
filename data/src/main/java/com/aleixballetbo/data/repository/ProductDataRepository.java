package com.aleixballetbo.data.repository;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.entities.Product;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class ProductDataRepository implements ProductRepository{

    private final DataSource dataSource;

    @Inject
    public ProductDataRepository (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getProducts(GetProductsCallback callback) {
        try {
            List<Product> productList = dataSource.getProducts();
            callback.onSuccess(productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getProductDetail(String productId, GetProductDetailCallback callback) {
        try {
            Product product = dataSource.getProductDetail(productId);
            callback.onSuccess(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(Product product, AddProductCallback callback) {

    }
}
