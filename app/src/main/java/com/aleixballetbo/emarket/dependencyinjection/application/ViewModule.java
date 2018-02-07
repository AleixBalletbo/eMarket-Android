package com.aleixballetbo.emarket.dependencyinjection.application;


import com.aleixballetbo.emarket.main.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private MainView view;

    public ViewModule(MainView view) {
        this.view = view;
    }

    @Provides
    MainView providesMainView(){
        return view;
    }
}
