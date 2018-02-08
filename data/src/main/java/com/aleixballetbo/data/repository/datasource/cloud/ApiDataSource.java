package com.aleixballetbo.data.repository.datasource.cloud;

import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.network.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiDataSource implements DataSource {

    @Inject
    public ApiDataSource () {
    }

    @Inject
    Retrofit retrofit;

    @Override
    public List<Product> getProducts() throws IOException {

        ProductService productService = retrofit.create(ProductService.class);

        Response<List<Product>> productsResponse = productService.getProducts().execute();
        return productsResponse.body();
    }
}
