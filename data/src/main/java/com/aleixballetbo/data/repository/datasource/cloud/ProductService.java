package com.aleixballetbo.data.repository.datasource.cloud;


import com.aleixballetbo.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{productId}")
    Call<Product> getProductDetail(@Path("productId") String id);
}
