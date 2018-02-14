package com.aleixballetbo.data.dependencyinjection;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.data.repository.ProductDataRepository;
import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.data.repository.datasource.cloud.ApiDataSource;
import com.aleixballetbo.data.repository.datasource.cloud.ProductService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ProductRepository providesProductRepository (ProductDataRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    public DataSource providesDataSource(ApiDataSource apiDataSource){
        return apiDataSource;
    }

    @Provides
    @Singleton
    public ProductService providesProductService (Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://emarket-backend.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
