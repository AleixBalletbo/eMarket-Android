package com.aleixballetbo.data.repository.datasource.cloud;

import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.data.repository.datasource.cloud.model.Mapper;
import com.aleixballetbo.data.repository.datasource.cloud.model.ProductDTO;
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
        Response<List<ProductDTO>> productsResponse = productService.getProducts().execute();
        if (!productsResponse.isSuccessful()) {
            throw new IOException();
        }
        return Mapper.map(productsResponse.body());
    }

    @Override
    public Product getProductDetail(String productId) throws IOException {
        Response<ProductDTO> productResponse = productService.getProductDetail(productId).execute();
        if (!productResponse.isSuccessful()) {
            throw new IOException();
        }
        return Mapper.map(productResponse.body());
    }

    @Override
    public void addProduct(Product product) throws IOException {
        Response<Void> addProductResponse = productService.postProduct(product).execute();
        if (!addProductResponse.isSuccessful()) {
            throw new IOException();
        }
    }
}
