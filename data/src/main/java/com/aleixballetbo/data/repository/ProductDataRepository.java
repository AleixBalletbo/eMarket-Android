package com.aleixballetbo.data.repository;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;

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
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return "No s'ha pogut carregar la llista de productes";
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void getProductDetail(String productId, GetProductDetailCallback callback) {
        try {
            Product product = dataSource.getProductDetail(productId);
            callback.onSuccess(product);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return "No s'ha pogut carregar el detall d'aquest producte";
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void addProduct(Product product, AddProductCallback callback) {
        try {
            dataSource.addProduct(product);
            callback.onSuccess(null);
        }
        catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return "No s'ha pogut afegir aquest producte";
                }
            };
            callback.onError(errorBundle);
        }
    }
}
