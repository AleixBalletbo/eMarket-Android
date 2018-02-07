package com.aleixballetbo.data.dependencyinjection;


import com.aleixballetbo.data.repository.datasource.DataSource;
import com.aleixballetbo.data.repository.datasource.cloud.ApiDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public DataSource providesDataSource(ApiDataSource apiDataSource){
        return apiDataSource;
    }
}
