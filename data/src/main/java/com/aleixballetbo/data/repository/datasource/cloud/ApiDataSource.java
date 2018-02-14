package com.aleixballetbo.data.repository.datasource.cloud;

import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.entities.Product;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;


public class ApiDataSource implements DataSource {

    private final ProductService productService;

    @Inject
    public ApiDataSource (ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getProducts() throws IOException {
        Response<List<Product>> productsResponse = productService.getProducts().execute();
        return productsResponse.body();
    }

    @Override
    public Product getProductDetail(String productId) throws IOException {
        Response<Product> productResponse = productService.getProductDetail(productId).execute();
        return productResponse.body();
    }
}
