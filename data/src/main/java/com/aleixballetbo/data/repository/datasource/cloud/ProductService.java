package com.aleixballetbo.data.repository.datasource.cloud;


import com.aleixballetbo.data.repository.datasource.cloud.model.ProductDTO;
import com.aleixballetbo.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {

    @GET("products")
    Call<List<ProductDTO>> getProducts();

    @GET("products/{productId}")
    Call<ProductDTO> getProductDetail(@Path("productId") String id);

    @POST("products")
    Call<Void> postProduct(@Body Product product);
}
