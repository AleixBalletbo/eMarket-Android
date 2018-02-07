package com.aleixballetbo.data.repository.datasource.cloud;

import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.network.ProductService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiDataSource implements DataSource {

    @Inject
    public ApiDataSource () {

    }

    @Override
    public List<Product> getProducts() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emarket-backend.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);

        Response<List<Product>> productsResponse = productService.getProducts().execute();
        return productsResponse.body();
    }
}
