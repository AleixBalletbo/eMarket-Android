package com.aleixballetbo.emarket.dependencyinjection.application;

import android.content.Context;

import com.aleixballetbo.data.dependencyinjection.DataModule;
import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.dependencyinjection.qualifier.ForApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataModule.class})
public class ApplicationModule {


    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    public Context providesContext(){
        return app;
    }

    /*

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(PicassoLoader picassoLoader) {
        return picassoLoader;
    }
    */
}
