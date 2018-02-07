package com.aleixballetbo.data.dependencyinjection;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.data.repository.ProductDataRepository;
import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.data.repository.datasource.cloud.ApiDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
