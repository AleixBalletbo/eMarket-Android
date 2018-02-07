package com.aleixballetbo.network;


import com.aleixballetbo.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products")
    Call<List<Product>> getProducts();
}
