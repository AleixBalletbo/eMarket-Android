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
        return Mapper.map(productsResponse.body());
    }

    @Override
    public Product getProductDetail(String productId) throws IOException {
        Response<ProductDTO> productResponse = productService.getProductDetail(productId).execute();
        return Mapper.map(productResponse.body());
    }
}
