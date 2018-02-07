package com.aleixballetbo.emarket;

import android.app.Application;

import com.aleixballetbo.emarket.dependencyinjection.application.ApplicationComponent;
import com.aleixballetbo.emarket.dependencyinjection.application.ApplicationModule;
import com.aleixballetbo.emarket.dependencyinjection.application.DaggerApplicationComponent;

public class App extends Application {

    ApplicationComponent component = null;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }
}
